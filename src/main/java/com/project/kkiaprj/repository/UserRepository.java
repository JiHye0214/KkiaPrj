package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByLoginId(String loginId);
    User findByNickname(String nickname);
    User findByEmail(String email);

}
