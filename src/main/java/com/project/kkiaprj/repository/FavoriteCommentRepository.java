package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FavoriteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteCommentRepository extends JpaRepository<FavoriteComment, Long> {
}
