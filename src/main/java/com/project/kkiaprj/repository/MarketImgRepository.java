package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.MarketImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketImgRepository extends JpaRepository<MarketImg, Long> {

    List<MarketImg> findByMarketId(Long marketId);

}
