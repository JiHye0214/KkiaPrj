package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.Favorite;
import com.project.kkiaprj.domain.Market;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketRepository extends JpaRepository<Market, Long> {
    Page<Market> findByProductContains(String sq, Pageable pageable);

}
