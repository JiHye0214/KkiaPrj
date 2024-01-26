package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Market;
import com.project.kkiaprj.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    // 마켓 리스트
    @Override
    public List<Market> getMarketList(Model model) {

        List<Market> marketList = new ArrayList<>();

        marketList = marketRepository.findAll();

        model.addAttribute("marketList", marketList);

        return marketList;
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

    // 마켓 작성
    @Override
    public int writeMarket(Market market) {
        market.setUser(U.getLoggedUser());
        marketRepository.saveAndFlush(market);
        return 1;
    }
}
