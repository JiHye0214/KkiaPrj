package com.project.kkiaprj.controller;

import com.project.kkiaprj.service.FavoriteService;
import com.project.kkiaprj.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private GameService gameService;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/")
    public String home_() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home(Model model) {
        gameService.homeRender(model);
        model.addAttribute("top5Favorites", favoriteService.findTop5());
    }
}
