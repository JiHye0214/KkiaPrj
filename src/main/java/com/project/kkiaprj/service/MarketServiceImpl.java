package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.*;
import com.project.kkiaprj.repository.MarketImgRepository;
import com.project.kkiaprj.repository.MarketRepository;
import com.project.kkiaprj.repository.MarketTalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class MarketServiceImpl implements MarketService {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Autowired
    private MarketRepository marketRepository;
    @Autowired
    private MarketImgRepository marketImgRepository;
    @Autowired
    private MarketTalkRepository marketTalkRepository;

    // 마켓 리스트
    @Override
    public List<Market> getMarketList(Integer page, String sq, Model model) {
        if (page < 1) page = 1;

        int pagesPerSection = 5;
        int rowsPerPage = 10;

        Page<Market> pagedMarket = null;
        if (sq.isEmpty()) {
            pagedMarket = marketRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));;
        } else {
            pagedMarket = marketRepository.findByProductContains(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        }

        long totalLength = pagedMarket.getTotalElements();
        int totalPage = pagedMarket.getTotalPages();

        int startPage = 0;
        int endPage = 0;

        List<Market> lists = new ArrayList<>();

        if (totalLength > 0) {
            if (page > totalPage) {
                page = totalPage;

                if (sq.isEmpty()) {
                    pagedMarket = marketRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                } else {
                    pagedMarket = marketRepository.findByProductContains(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                }
            }

            startPage = (((page - 1) / pagesPerSection) * pagesPerSection) + 1;
            endPage = startPage + pagesPerSection - 1;
            if (endPage > totalPage) endPage = totalPage;

            lists = pagedMarket.getContent();

            model.addAttribute("lists", lists);
        } else {
            page = 0;
        }

        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("sq", sq);

        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return lists;
    }

    // 마켓 상세 + 이미지
    @Override
    public Market getMarket(Long id) {
        Market market = marketRepository.findById(id).orElse(null); // 아 이렇게 해야 Market으로 데려올 수 있구나 아니면 Optional로 데려옴
        if(market != null) {
            market.setViewCnt(market.getViewCnt() + 1);
            marketRepository.saveAndFlush(market);
        }
        return market;
    }

    // 마켓 삭제
    @Override
    public int deleteMarket(Market market) {

        Market delete = marketRepository.findById(market.getId()).orElse(null);
        assert delete != null;
        marketRepository.delete(delete);
        return 1;
    }

    // 마켓 수정
    @Override
    public int modifyMarket(Market market,
                            Map<String, MultipartFile> file,
                            Long[] delFiles
    ) {

        resistImg(file, market); // 새로운 파일 첨부

        if(delFiles != null){
            for(Long fileId : delFiles) {
                MarketImg img = marketImgRepository.findById(fileId).orElse(null);
                if(img != null) {
                    img.setMarketId(market.getId());
                    deleteImgs(img); // upload 삭제
                    marketImgRepository.delete(img); // db 삭제
                }
            }
        }

        // 유저이미지에 market id 넣어주거나
        // 마켓에 유저이미지 넣어주거나 해야 됨
        Market update = marketRepository.findById(market.getId()).orElse(null);
        List<MarketImg> marketImg = marketImgRepository.findByMarketId(market.getId());

        assert update != null;
        update.setProduct(market.getProduct());
        update.setPrice(market.getPrice());
        update.setRegion(market.getRegion());
        update.setContent(market.getContent());
        update.setUser(U.getLoggedUser());
        update.setMarketImgs(marketImg);
        marketRepository.saveAndFlush(update);

        return 1;
    }

    private void deleteImgs(MarketImg img) {
        String saveDirectory = new File(uploadDir).getAbsolutePath();

        File f = new File(saveDirectory, img.getFileName()); // 원본이 삭제 대상
        if(f.exists()) {
            f.delete();
        }
    }

    // 마켓 작성
    @Override
    @Transactional
    public int writeMarket(Market market, Map<String, MultipartFile> file) {

        market.setUser(U.getLoggedUser());
        marketRepository.saveAndFlush(market);

        resistImg(file, market);

        return 1;
    }

    public void resistImg(Map<String, MultipartFile> file, Market market){

        if(file == null) return; // 없으면 말고

        for(Map.Entry<String, MultipartFile> e : file.entrySet()){

            // 이름이 userImg 인 친구들만
            if(!e.getKey().startsWith("upload")) continue;

            // 물리적 파일 저장
            MarketImg marketImg = upload(e.getValue()); // 함수가 UserImg 타입을 반환

            if(marketImg != null) {
                marketImg.setMarketId(market.getId());
                marketImgRepository.saveAndFlush(marketImg);
            }
        }
    }

    public MarketImg upload(MultipartFile multipartFile) {

        MarketImg marketImg = null;

        String sourceName = null;
        String fileName = null;

        String originalFilename = multipartFile.getOriginalFilename();
        if(originalFilename == null || originalFilename.isEmpty()) return null;

        sourceName = StringUtils.cleanPath(originalFilename);
        fileName = sourceName;

        File file = new File(uploadDir, fileName);

        if(file.exists()){  // 이미 존재하는 파일명,  중복된다면 다른 이름은 변경하여 파일 저장
            // a.txt => a_2378142783946.txt  : time stamp 값을 활용할거다!
            // "a" => "a_2378142783946" : 확장자 없는 경우

            int pos = fileName.lastIndexOf(".");
            if(pos > -1){  // 확장자 있는 경우
                String name = fileName.substring(0, pos);   // 파일 '이름'
                String ext = fileName.substring(pos + 1);  // 파일 '확장자'

                fileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {  // 확장자 없는 경우
                fileName += "_" + System.currentTimeMillis();
            }
        }

        Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());

        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        marketImg = MarketImg.builder()
                    .fileName(fileName)
                    .sourceName(sourceName)
                    .build();

        return marketImg;
    }

    // 채팅
    @Override
    public List<MarketTalk> getMarketTalk(Long marketId) {
        Long userId = U.getLoggedUser().getId();
        return marketTalkRepository.findByMarketIdAndUserId(marketId, userId);
    }
    @Override
    public void writeTalk(MarketTalk marketTalk) {
        marketTalk.setUser(U.getLoggedUser());
        marketTalkRepository.saveAndFlush(marketTalk);
    }
}
