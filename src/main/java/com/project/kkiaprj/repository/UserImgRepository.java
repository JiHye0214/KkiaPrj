package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.UserImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImgRepository  extends JpaRepository<UserImg, Long> {
    UserImg findByUserId(Long userId);
    void removeByUserId(Long userId);
}
