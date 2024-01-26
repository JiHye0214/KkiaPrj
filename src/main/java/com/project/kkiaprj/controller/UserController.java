package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.config.UserInformation;
import com.project.kkiaprj.domain.GameRecord;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import com.project.kkiaprj.domain.UserValidator;
import com.project.kkiaprj.service.CommunityService;
import com.project.kkiaprj.service.UserMypageService;
import com.project.kkiaprj.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.lang.System.err;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMypageService userMypageService;

    @GetMapping("/logIn")
    public void logIn(){}

    // 로그인 에러
    @PostMapping("/loginError")
    public String loginError(){
        return "user/logIn";
    }

    @GetMapping("/find")
    public void find(){}

    @GetMapping("/signUp")
    public void signUp(){};

    // 회원가입
    @PostMapping("/signUp")
    public String signUp(@Valid User user,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {

        // 검증 에러가 있으면 redirect
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("loginId", user.getLoginId());
            redirectAttributes.addFlashAttribute("email", user.getEmail());
            redirectAttributes.addFlashAttribute("nickname", user.getNickname());
            redirectAttributes.addFlashAttribute("password", user.getPassword());
            redirectAttributes.addFlashAttribute("name", user.getName());

            List<FieldError> errList = result.getFieldErrors();
            for(FieldError err : errList){
                redirectAttributes.addFlashAttribute("error", err.getCode());
                break;
            }

            return "redirect:/user/signUp";
        }

        int resister = userService.resister(user);
        model.addAttribute("result", resister);
        return "user/signUpOk";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam(name = "menu", required = false, defaultValue = "커뮤니티")String menu,
                         @RequestParam(name = "nicknameErr", required = false, defaultValue = "")String nicknameErr,
                         @RequestParam(name = "passwordErr", required = false, defaultValue = "")String passwordErr,
                         Model model,
                         RedirectAttributes redirectAttrs
    ){
        // 직관 기록 리스트
        List<GameRecord> gameRecords = userMypageService.getGameRecord(U.getLoggedUser().getId());
        model.addAttribute("gameRecords", gameRecords);

        // 프사
        UserImg userImg = userMypageService.getUserImg(U.getLoggedUser().getId());
        model.addAttribute("userImg", userImg);

        // menu select
        if (menu.equals("커뮤니티") || menu.equals("직관기록") || menu.equals("회원정보")) {
            userMypageService.setMypage(menu, model);
            return "user/mypage";
        } else {
            redirectAttrs.addAttribute("menu", "커뮤니티");
            return "redirect:/user/mypage";
        }
    }

    @PostMapping("/mypageType")
    public String mypageType(String menu, RedirectAttributes redirectAttrs){
        redirectAttrs.addAttribute("menu", menu);
        return "redirect:/user/mypage";
    }

    @PostMapping("/addOrUpdateGameRecord")
    public String addGameRecord(GameRecord gameRecord, RedirectAttributes redirectAttrs){
        gameRecord.setUser(U.getLoggedUser());
        userMypageService.addOrUpdateGameRecord(gameRecord);
        redirectAttrs.addAttribute("menu", "직관기록");
        return "redirect:/user/mypage";
    }
    @PostMapping("/deleteGameRecord")
    public String deleteGameRecord(GameRecord gameRecord, RedirectAttributes redirectAttrs) {
        gameRecord.setUser(U.getLoggedUser());
        userMypageService.deleteGameRecord(gameRecord);
        redirectAttrs.addAttribute("menu", "직관기록");
        return "redirect:/user/mypage";
    }

    // ↓ 그냥 수정이랑 초기화 누르고 수정이랑 뭔가 깔끔하게 안에서 나눌 수 없어서 따로 뺐다...
    @PostMapping("/resetImg") // 초기화
    public String resetImg(@RequestParam Map<String, MultipartFile> file,
                         RedirectAttributes redirectAttrs) {
        boolean resetImg = true;
        userMypageService.setUserImg(file, resetImg);
        redirectAttrs.addAttribute("menu", "회원정보");
        return "redirect:/user/mypage";
    }
    @PostMapping("/modifyUser")
    public String modifyUser(User user,
                             @RequestParam Map<String, MultipartFile> file,
                             String newPassword,
                             @AuthenticationPrincipal UserInformation userInformation,
                             RedirectAttributes redirectAttrs) {

        // 원래 user
        User origin = U.getLoggedUser();

        // 닉네임, 생일, 비번, 성별, 프사
        if(!Objects.equals(user.getNickname(), origin.getNickname())) { // 이전과 다르면
            userMypageService.setNickname(user.getNickname(), redirectAttrs);
        }
        if(!Objects.equals(user.getBirth(), origin.getBirth())) { // 이전과 다르면
            userMypageService.setBirth(user.getBirth());
        }
        if(!Objects.equals(user.getPassword(), "")) { // 빈 거 아니면
            if(passwordEncoder.matches(user.getPassword(), origin.getPassword())) { // 비번 맞으면
                userMypageService.setPassword(newPassword);
            } else {
                redirectAttrs.addFlashAttribute("passwordErr", "* 현재 비밀번호가 올바르지 않습니다");
            }
        }
        if(!Objects.equals(user.getGender(), origin.getGender())) { // 이전과 다르면
            userMypageService.setGender(user.getGender());
        }

        // 프사
        boolean resetImg = false;
        userMypageService.setUserImg(file, resetImg);

        redirectAttrs.addAttribute("menu", "회원정보");
        return "redirect:/user/mypage";
    }

// ------------------validator--------------------
    @InitBinder("user")
    public void intiBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator(userService));
    }
}



