package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.GameRecord;
import com.project.kkiaprj.domain.UserImg;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface UserMypageService {

    // 메뉴 선택
    void setMypage(String menu, Model model);

    // 직관 기록 ------------------------------------------

    // 직관 기록 가져오기
    List<GameRecord> getGameRecord(Long userId);

    // 직관 기록 추가
    void addGameRecord(GameRecord gameRecord);

    // 회원 정보 ------------------------------------------

    // 회원 이미지 가져오기
    UserImg getUserImg(Long userId);

    // 회원정보 변경
    void setNickname(String nickname, RedirectAttributes redirectAttrs);
    void setBirth(LocalDate birth);
    void setPassword(String newPassword);
    void setGender(String gender);
    void setUserImg(Map<String, MultipartFile> file, boolean resetImg);

}
