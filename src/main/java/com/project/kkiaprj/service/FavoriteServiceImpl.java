package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Favorite;
import com.project.kkiaprj.domain.FavoriteImg;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.FavoriteImgRepository;
import com.project.kkiaprj.repository.FavoriteRepository;
import com.project.kkiaprj.repository.UserRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteImgRepository favoriteImgRepository;

    // 최애 글 목록 조회 (페이징)
    @Override
    public List<Favorite> list(Integer page, String sq, Model model) {
        if (page < 1) page = 1;

        int pagesPerSection = 5;
        int rowsPerPage = 10;

        Page<Favorite> pagedFavorite = null;
        if (sq.isEmpty()) {
            pagedFavorite = favoriteRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        } else {
            pagedFavorite = favoriteRepository.findByPlayerNameContainsOrPlayerNumContains(sq, sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        }

        long totalLength = pagedFavorite.getTotalElements();
        int totalPage = pagedFavorite.getTotalPages();

        int startPage = 0;
        int endPage = 0;

        List<Favorite> lists = new ArrayList<>();

        if (totalLength > 0) {
            if (page > totalPage) {
                page = totalPage;

                if (sq.isEmpty()) {
                    pagedFavorite = favoriteRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                } else {
                    pagedFavorite = favoriteRepository.findByPlayerNameContainsOrPlayerNumContains(sq, sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                }
            }

            startPage = (((page - 1) / pagesPerSection) * pagesPerSection) + 1;
            endPage = startPage + pagesPerSection - 1;
            if (endPage > totalPage) endPage = totalPage;

            lists = pagedFavorite.getContent();
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

    // 최애 글 상세 조회 (조회수 증가X)
    @Override
    public Favorite detailById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    // 최애 글 상세 조회 (조회수 증가O)
    @Override
    @Transactional
    public Favorite detail(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElse(null);

        if (favorite != null) {
            favorite.setViewCnt(favorite.getViewCnt() + 1);
            favoriteRepository.saveAndFlush(favorite);
        }

        return favorite;
    }

    // 최애 글 작성
    @Override
    public int write(Map<String, MultipartFile> files, Favorite favorite) {
        int result = 0;

        User user = U.getLoggedUser();
        user = userRepository.findById(user.getId()).orElse(null);

        if (user != null) {
            favorite.setUser(user);
            favorite = favoriteRepository.saveAndFlush(favorite);

            // 이미지 파일들 저장
            addImgs(files, favorite.getId());

            result = 1;
        }

        return result;
    }

    // 이미 파일들 저장 함수
    private void addImgs(Map<String, MultipartFile> files, Long favoriteId) {
        for (Map.Entry<String, MultipartFile> e : files.entrySet()) {
            if (!e.getKey().startsWith("uploadImg")) continue;

            // 물리적으로 파일 저장 (kkiaPrj 프로젝트의 upload 폴더에 저장)
            FavoriteImg favoriteImg = upload(e.getValue());

            // 각 file 확인해서 null 이 아니면(담긴 파일 있으면) db 에도 저장
            if (favoriteImg != null) {
                favoriteImg.setFavoriteId(favoriteId);
                favoriteImgRepository.saveAndFlush(favoriteImg);
            }
        }
    }

    // 물리적으로 파일 저장 - 중복된 fileName 재설정
    private FavoriteImg upload(MultipartFile multipartFile) {
        FavoriteImg favoriteImg = null;

        // 담긴 파일 없으면 null return
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.length() == 0) return null;

        String imgSourceName = StringUtils.cleanPath(originalFilename); // 원본 파일명
        String imgFileName = imgSourceName; // 저장될 파일명

        // 파일명 중복되는지 확인
        File file = new File(uploadDir, imgFileName); // uploadDir 폴더에서 fileName 이름의 파일 가져오기

        if (file.exists()) {
            int pos = imgFileName.lastIndexOf(".");

            // 확장자 있다면
            if (pos > -1) {
                String name = imgFileName.substring(0, pos);  // 파일 '이름'
                String ext = imgFileName.substring(pos + 1);  // 파일 '확장자'
                imgFileName = name + "_" + System.currentTimeMillis() + "." + ext;
            } else {
                // 확장자 없다면 (확장자 없는 경우 -1 리턴)
                imgFileName += "_" + System.currentTimeMillis();
            }
        }

        // 저장 위치
        Path copyOfLocation = Paths.get(new File(uploadDir, imgFileName).getAbsolutePath());

        // copyOfLocation(저장 위치) 로 파일 쓰기
        try {
            Files.copy(
                    multipartFile.getInputStream(),
                    copyOfLocation,
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        favoriteImg = favoriteImg.builder()
                .fileName(imgFileName)
                .sourceName(imgSourceName)
                .build();

        return favoriteImg;
    }

    // 최애 글 삭제
    @Override
    public int delete(Long id) {
        int result = 0;
        Favorite favorite = favoriteRepository.findById(id).orElse(null);

        if (favorite != null) {
            favoriteRepository.delete(favorite);
            result = 1;
        }

        return result;
    }

}
