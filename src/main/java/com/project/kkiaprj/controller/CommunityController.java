package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.LiveChat;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.service.CommunityService;
import com.project.kkiaprj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommunityService communityService;

    // live chat 가져오기
    @GetMapping("/main")
    public void communityHome(Model model){
        model.addAttribute("chat", communityService.getChat());
    }

    // 채팅 쓰기
    @PostMapping("/writeChat")
    public String writeChat(LiveChat chat){

        chat.setUser(U.getLoggedUser());
        communityService.writeChat(chat);

        return "redirect:/community/main";
    }

    //---------------------------------------------------------------
    @GetMapping("/food/list")
    public void foodList() {
    }

    //---------------------------------------------------------------
    @GetMapping("/favorite/list")
    public void favoriteList(){}

    //---------------------------------------------------------------
    @GetMapping("/post/list")
    public void postList(){}

}
