package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodSaveRepository extends JpaRepository<FoodSave, Long> {

    @Query(value = "DROP TABLE IF EXISTS food_save", nativeQuery = true)
    @Modifying
    @Transactional
    int dropTable();

    @Query(value = "ALTER TABLE food_save AUTO_INCREMENT = 1", nativeQuery = true)
    @Modifying
    @Transactional
    int setIdAsOne();

    FoodSave findByUserIdAndFoodId(Long userId, Long foodId);

}
