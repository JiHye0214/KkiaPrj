package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);
    User findByLogId(String loginId);

    // 회원가입
    int resister(User user);

    // 회원가입 - 등록된 아이디(loginId)인지 확인
    boolean isExistId(String loginId);

    // 회원가입 - 등록된 닉네임인지 확인
    boolean isExistNn(String nickname);

    // 회원가입 - 등록된 이메일인지 확인
    boolean isExistEm(String email);
}
