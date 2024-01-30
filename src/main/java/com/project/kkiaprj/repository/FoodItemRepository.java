package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

    List<FoodItem> findByFoodId(Long foodId);

}
