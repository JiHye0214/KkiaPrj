package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostImgRepository extends JpaRepository<PostImg, Long> {
    List<PostImg> findByPostId(Long id);
}
