package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public interface UserService {

    // 마이페이지 접근 시 LazyInitializationException 발생 막기 위해 유저 찾기
    User findloggedUser(Long id);

    User findByLogId(String userId);

    // 찾기
    void setFindPage(String what, Model model);
    void findResult(User user, RedirectAttributes redirectAttrs);
    int updatePassword(User user);

    // 회원가입
    int resister(User user);

    // 회원가입 - 등록된 아이디(loginId)인지 확인
    boolean isExistId(String loginId);

    // 회원가입 - 등록된 닉네임인지 확인
    boolean isExistNn(String nickname);

    // 회원가입 - 등록된 이메일인지 확인
    boolean isExistEm(String email);

    // 회원탈퇴
    int dropUser(User user);

}
