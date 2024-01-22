package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String home_() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home() {
    }
//    @GetMapping("/user/find")
//    public void func2() {
//    }@GetMapping("/user/login")
//    public void func3() {
//    }@GetMapping("/user/mypage")
//    public void func4() {
//    }@GetMapping("/user/signUp")
//    public void func5() {
//    }@GetMapping("/market/marketDetail")
//    public void func6() {
//    }@GetMapping("/market/marketList")
//    public void func7() {
//    }@GetMapping("/market/marketUpdate")
//    public void func8() {
//    }@GetMapping("/market/marketWrite")
//    public void func9() {
//    }@GetMapping("/game/game")
//    public void func11() {
//    }@GetMapping("/community/favorite/favoriteDetail")
//    public void func12() {
//    }@GetMapping("/community/favorite/favoriteList")
//    public void func13() {
//    }@GetMapping("/community/favorite/favoriteUpdate")
//    public void func14() {
//    }@GetMapping("/community/favorite/favoriteWrite")
//    public void func15() {
//    }@GetMapping("/community/food/foodDetail")
//    public void func16() {
//    }@GetMapping("/community/food/foodList")
//    public void func17() {
//    }@GetMapping("/community/food/foodUpdate")
//    public void func18() {
//    }@GetMapping("/community/food/foodWrite")
//    public void func19() {
//    }@GetMapping("/community/post/postDetail")
//    public void func20() {
//    }@GetMapping("/community/post/postList")
//    public void func21() {
//    }@GetMapping("/community/post/postUpdate")
//    public void func22() {
//    }@GetMapping("/community/post/postWrite")
//    public void func23() {
//    }@GetMapping("/community/community")
//    public void func24() {
//    }
//

}
