package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public interface UserService {

    User findByLogId(String loginId);

    // 찾기
    void findResult(String what, User user, RedirectAttributes redirectAttrs);

    // 회원가입
    int resister(User user);

    // 회원가입 - 등록된 아이디(loginId)인지 확인
    boolean isExistId(String loginId);

    // 회원가입 - 등록된 닉네임인지 확인
    boolean isExistNn(String nickname);

    // 회원가입 - 등록된 이메일인지 확인
    boolean isExistEm(String email);

}
