package com.project.kkiaprj.controller;

import com.project.kkiaprj.domain.GamePlayer;
import com.project.kkiaprj.domain.GameSchedule;
import com.project.kkiaprj.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    GameService gameService;

    @GetMapping("/")
    public String home_() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public void home(Model model) {
        gameService.homeRender(model);
    }
}
