package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Page<Food> findByRegion(String region, Pageable pageable);

}
