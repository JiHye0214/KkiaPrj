package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.domain.FoodItem;
import com.project.kkiaprj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FoodCommentRepository extends JpaRepository<FoodComment, Long> {
}
