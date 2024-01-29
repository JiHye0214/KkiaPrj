package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Market;
import com.project.kkiaprj.domain.MarketTalk;
import com.project.kkiaprj.domain.MarketTalkList;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MarketService {

    // 전체 조회
    List<Market> getMarketList(Integer page, String sq, Model model);

    // 하나 조회
    Market getMarket(Long id);

    // 작성
    int writeMarket(Market market, Map<String, MultipartFile> file);
    // 삭제
    int deleteMarket(Market market);
    // 수정
    int modifyMarket(Market market, Map<String, MultipartFile> file, Long[] delFiles);

    // 채팅 리스트
    void headerMessageAlert(Long userId, Model model);
    List<MarketTalkList> getMarketTalkList(Long userId, Model model);
    // 채팅 내용
    List<MarketTalk> getMarketTalk(Long recipientId, Model model);
    int writeTalk(MarketTalk marketTalk);
}
