package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import com.project.kkiaprj.repository.UserImgRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByLogId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

    @Override
    public int resister(User user) {
            // password encode
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            // sign up
        userRepository.save(user);
        // authority setting
//            userAuthorityRepository.addAuthority(user.getId(), 1L);

            // default img setting
            UserImg userImg = UserImg.builder()
                    .user(user)
                    .sourceName("default.png")
                    .fileName("default.png")
                    .build();

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

    // 프사 조회
    @Override
    public UserImg findUserImgByUserId(Long id) {
        return userImgRepository.findByUserId(id);
    }

}
