package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.MarketTalk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTalkRepository extends JpaRepository<MarketTalk, Long> {

    List<MarketTalk> findByMarketTalkListId(Long marketTalkListId);

}
