package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    @Query(value = "DROP TABLE IF EXISTS food_item", nativeQuery = true)
    @Modifying
    @Transactional
    int dropTable();

    @Query(value = "ALTER TABLE food_item AUTO_INCREMENT = 1", nativeQuery = true)
    @Modifying
    @Transactional
    int setIdAsOne();

    List<FoodItem> findByFood(Long foodId);

}
