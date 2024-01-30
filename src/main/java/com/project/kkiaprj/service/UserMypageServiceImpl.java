package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.*;
import com.project.kkiaprj.repository.FoodCommentRepository;
import com.project.kkiaprj.repository.GameRecordRepository;
import com.project.kkiaprj.repository.UserImgRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserMypageServiceImpl implements UserMypageService {

    @Value("${app.upload.path}")
    private String uploadDir;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserImgRepository userImgRepository;

    @Autowired
    GameRecordRepository gameRecordRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void setMypage(String menu, Model model) {
        String menuValue = "";
        if(menu.equals("글 관리")){
            menuValue = "글 관리";
        } else if(menu.equals("직관기록")) {
            menuValue = "직관기록";
        } else {
            menuValue = "회원정보";
        }

        model.addAttribute("menu", menuValue);
    }

    // 직관 기록 ----------------------------------------------------------------------------------

    @Override
    public List<GameRecord> getGameRecord(Long userId) {
        return gameRecordRepository.findByUserId(userId);
    }

    @Override
    public void addOrUpdateGameRecord(GameRecord gameRecord) {
        GameRecord prevRecord = gameRecordRepository.findByUserIdAndRecordDate(gameRecord.getUser().getId(), gameRecord.getRecordDate());
        if(prevRecord != null) { // 같은 게 있으면
            gameRecordRepository.delete(prevRecord);
            gameRecordRepository.saveAndFlush(gameRecord);
        } else {
            gameRecordRepository.saveAndFlush(gameRecord);
        }
    }

    @Override
    public void deleteGameRecord(GameRecord gameRecord) {
        GameRecord deleteRecord = gameRecordRepository.findByUserIdAndRecordDate(gameRecord.getUser().getId(), gameRecord.getRecordDate());
        gameRecordRepository.delete(deleteRecord);
    }

    // 회원 정보 ----------------------------------------------------------------------------------

    // 회원정보 변경
    @Override
    public void setNickname(String nickname, RedirectAttributes redirectAttrs) {
        if(userRepository.findByNickname(nickname) != null){ // 중복 있으면
            redirectAttrs.addFlashAttribute("nicknameErr", "nicknameErr");
        } else {
            User origin = U.getLoggedUser();
            origin.setNickname(nickname);
            userRepository.saveAndFlush(origin);
        }
    }

    @Override
    public void setBirth(LocalDate birth) {
        User origin = U.getLoggedUser();
        origin.setBirth(birth);
        userRepository.saveAndFlush(origin);
    }

    @Override
    public void setPassword(String newPassword) {
        User origin = U.getLoggedUser();
        origin.setPassword(passwordEncoder.encode(newPassword));
        userRepository.saveAndFlush(origin);
    }

    @Override
    public void setGender(String gender) {
        User origin = U.getLoggedUser();
        origin.setGender(gender);
        userRepository.saveAndFlush(origin);
    }

    @Override
    @Transactional
    public void setUserImg(Map<String, MultipartFile> file, boolean resetImg) {
        Long userId = U.getLoggedUser().getId();
        User user = U.getLoggedUser();

        UserImg userImg = userImgRepository.findByUserId(userId); // 일단 저장

        changeImg(file, user, userImg, resetImg);
    }

    private void changeImg(Map<String, MultipartFile> file,
                           User user,
                           UserImg userImg,
                           boolean resetImg) {

        Long imgId = userImg.getId();

        if(file == null) return; // 없으면 말고

        for(Map.Entry<String, MultipartFile> e : file.entrySet()){

            // 이름이 userImg 인 친구들만
            if(!e.getKey().startsWith("user")) continue;

            // 물리적 파일 저장
            UserImg profile = upload(e.getValue(), userImg, resetImg); // 함수가 UserImg 타입을 반환

            if(profile != null) {
                profile.setId(imgId); // id가 있어야 바뀐다.
                profile.setUserId(U.getLoggedUser().getId());
                userImgRepository.saveAndFlush(profile); // 이미지만 바꿀 게 아니라

                user.setUserImg(profile);
                userRepository.saveAndFlush(user); // 유저도 바꿔야지
            }
        }
    }

    private UserImg upload(MultipartFile multipartFile,
                           UserImg origin,
                           boolean resetImg) {

        // 리턴할 객체 선언
        UserImg userImg = null;
        String sourceName = null;
        String fileName = null;

        String originalFilename = multipartFile.getOriginalFilename(); // 새로운 원본파일명

        // 초기화 눌렀을 때
        if(originalFilename.isEmpty()) {
            if(!resetImg) {
                sourceName = origin.getSourceName();
            } else {
                sourceName = "default.png";
            }
            fileName = sourceName;
        } else {
            sourceName = StringUtils.cleanPath(originalFilename); // 경로 깨끗?

            // 저장될 파일명
            fileName = sourceName; // 일단 같은 이름으로 저장

            File file = new File(uploadDir, fileName); // 중복 확인

            if(file.exists()){  // 이미 존재하는 파일명,  중복된다면 다른 이름은 변경하여 파일 저장
                // a.txt => a_2378142783946.txt  : time stamp 값을 활용할 거다!
                // "a" => "a_2378142783946" : 확장자 없는 경우

                int pos = fileName.lastIndexOf(".");
                if(pos > -1){  // 확장자 있는 경우
                    String name = fileName.substring(0, pos);   // 파일 '이름'
                    String ext = fileName.substring(pos + 1);  // 파일 '확장자'

                    fileName = name + "_" + System.currentTimeMillis() + "." + ext;
                } else {  // 확장자 없는 경우
                    fileName += "_" + System.currentTimeMillis();
                }
            }

            // 파일 절대경로
            Path copyOfLocation = Paths.get(new File(uploadDir, fileName).getAbsolutePath());

            try {
                // inputStream을 가져와서
                // copyOfLocation (저장위치)로 파일을 쓴다.
                // copy의 옵션은 기존에 존재하면 REPLACE(대체한다), 오버라이딩 한다
                Files.copy(
                        multipartFile.getInputStream(),
                        copyOfLocation,
                        StandardCopyOption.REPLACE_EXISTING
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        userImg = UserImg.builder()
                .sourceName(sourceName)
                .fileName(fileName)
                .build();

        return userImg;
    }
}
