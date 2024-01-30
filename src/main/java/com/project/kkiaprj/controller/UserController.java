package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.GameRecord;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserValidator;
import com.project.kkiaprj.service.UserMypageService;
import com.project.kkiaprj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    // 찾기
    @GetMapping("/find")
    public String find(@RequestParam(name = "what", required = false, defaultValue = "id") String what,
                       @RequestParam(name = "warnMessage", required = false, defaultValue = "") String warnMessage,
                       @RequestParam(name = "warnMessage", required = false, defaultValue = "") String give,
                       @RequestParam(name = "warnMessage", required = false, defaultValue = "") String user,
                       Model model,
                       RedirectAttributes redirectAttrs
    ){

        if (what.equals("id") || what.equals("pw")) {
            userService.setFindPage(what, model);
            return "user/find";
        } else {
            redirectAttrs.addAttribute("what", "id");
            return "redirect:/user/find";
        }
    }

    @PostMapping("/findWhat")
    public String findWhat(String what, RedirectAttributes redirectAttrs){
        if(what.equals("아이디 찾기")) what = "id";
        if(what.equals("비밀번호 찾기")) what = "pw";

        redirectAttrs.addAttribute("what", what);
        return "redirect:/user/find";
    }

    @PostMapping("/findInform")
    public String findId(User user,
                         Model model,
                         RedirectAttributes redirectAttrs) {

        userService.findResult(user, redirectAttrs);
        return "redirect:/user/find";
    }

    @PostMapping("/findCngPw")
    public String findChangePw(User user, Model model){
        model.addAttribute("result", userService.updatePassword(user));
        model.addAttribute("action", "비밀번호 변경");
        return "user/success";
    }

    // 회원가입
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
        model.addAttribute("action", "회원가입");
        return "user/success";
    }

    @GetMapping("/mypage")
    public String mypage(@RequestParam(name = "menu", required = false, defaultValue = "글 관리")String menu,
                         @RequestParam(name = "nicknameErr", required = false, defaultValue = "")String nicknameErr,
                         @RequestParam(name = "passwordErr", required = false, defaultValue = "")String passwordErr,
                         Model model,
                         RedirectAttributes redirectAttrs
    ){
        Long userId = U.getLoggedUser().getId();

        // 마이페이지에서 유저가 작성한 마켓, 맛집, 최애, 자유 글 & 저장한 맛집 글 & 좋아요 한 최애 글 가져와야 하는데
        // mypage.html 에서 logged_user.markets 이런 식으로 가져오려 하면 LazyInitializationException 발생 (OneToMany 이기 때문)
        // 따라서 userId 로 로그인 한 user 찾아서 mypage.html 에 넘겨주기
        model.addAttribute("loggedUser", userService.findloggedUser(userId));

        // 직관 기록 리스트
        List<GameRecord> gameRecords = userMypageService.getGameRecord(userId);
        model.addAttribute("gameRecords", gameRecords);

        // menu select
        if (menu.equals("글 관리") || menu.equals("직관기록") || menu.equals("회원정보")) {
            userMypageService.setMypage(menu, model);
            return "user/mypage";
        } else {
            redirectAttrs.addAttribute("menu", "글 관리");
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
                             RedirectAttributes redirectAttrs) {

        // 원래 user
        User origin = U.getLoggedUser();

        // 닉네임, 생일, 비번, 성별, 프사
        if(!Objects.equals(user.getNickname(), origin.getNickname())) { // 이전과 다르면
            if(userService.isExistNn(user.getNickname())) {
                redirectAttrs.addFlashAttribute("nicknameErr", "* 중복된 닉네임입니다");
            } else {
                userMypageService.setNickname(user.getNickname(), redirectAttrs);
            }
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

    @PostMapping("/dropUser")
    public String dropUser(User user,
                           Model model,
                           HttpServletRequest request,
                           HttpServletResponse response) throws Exception {

        if(passwordEncoder.matches(user.getPassword(), U.getLoggedUser().getPassword())){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                model.addAttribute("result", userService.dropUser(user));
                model.addAttribute("action", "탈퇴");
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
        }
        return "user/success";
    }

    // ------------------validator--------------------
    @InitBinder("user")
    public void intiBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator(userService));
    }

}
