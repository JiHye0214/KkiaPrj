package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FoodCommentRepository extends JpaRepository<FoodComment, Long> {

    @Query(value = "DROP TABLE IF EXISTS food_comment", nativeQuery = true)
    @Modifying
    @Transactional
    int dropTable();

    @Query(value = "ALTER TABLE food_comment AUTO_INCREMENT = 1", nativeQuery = true)
    @Modifying
    @Transactional
    int setIdAsOne();

}
