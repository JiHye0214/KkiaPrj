package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {
    List<Market> findByProductContains(String sq); // 검색어 찾기
}
