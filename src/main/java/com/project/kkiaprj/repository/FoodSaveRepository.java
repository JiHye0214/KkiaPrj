package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodSaveRepository extends JpaRepository<FoodSave, Long> {

    FoodSave findByUserIdAndFoodId(Long userId, Long foodId);

}
