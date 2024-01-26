package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Market;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface MarketService {

    // 전체 조회
    List<Market> getMarketList(Model model);

    // 하나 조회
    Market getMarket(Long id);

    // 작성
    int writeMarket(Market market);
}
