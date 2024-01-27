package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import com.project.kkiaprj.repository.UserImgRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    // password Encoder
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImgRepository userImgRepository;

    @Override
    public User findByLogId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    // 찾기
    @Override
    public void findResult(String what, User user, RedirectAttributes redirectAttrs) {

        String warn = "";

        if(Objects.equals(what, "아이디")) {
            User result = userRepository.findByNameAndEmail(user.getName(), user.getEmail());
            if(result == null) {
                warn = "이름 또는 이메일이 올바르지 않습니다.";
            }
        } else if(Objects.equals(what, "비밀번호")) {
            User result = userRepository.findByNameAndLoginId(user.getName(), user.getLoginId());
            if(result == null) {
                warn = "이름 또는 아이디가 올바르지 않습니다.";
            }
        }

        System.out.println("======================================");
        System.out.println(warn);

        redirectAttrs.addAttribute("warn", warn);
    }

    // 회원가입
    @Override
    public int resister(User user) {
        // password encode
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // authority setting
//            userAuthorityRepository.addAuthority(user.getId(), 1L);

        // default img setting
        UserImg userImg = UserImg.builder()
                .sourceName("default.png")
                .fileName("default.png")
                .build();

        // sign up
        user.setUserImg(userImg);
        userRepository.saveAndFlush(user);

        // 이것도 해 줘야 됨 왜냐면 유저가 먼저 생성되어야 하는데 이미지 없이 생성이 안 됨
        // 근데 저러고 말면 이미지 테이블에는 userId가 null임 (당연함 userId를 안 줬기 때문이지)
        userImg.setUserId(user.getId());
        userImgRepository.saveAndFlush(userImg);

        return 1;
    }

    @Override
    public boolean isExistId(String loginId) {
        User user = userRepository.findByLoginId(loginId);
        return (user != null);
    }

    @Override
    public boolean isExistNn(String nickname) {
        User user = userRepository.findByNickname(nickname);
        return (user != null);
    }

    @Override
    public boolean isExistEm(String email) {
        User user = userRepository.findByEmail(email);
        return (user != null);
    }

}
