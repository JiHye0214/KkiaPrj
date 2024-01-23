package com.project.kkiaprj.controller;

import com.project.kkiaprj.domain.GameSchedule;
import com.project.kkiaprj.repository.GameRepository;
import com.project.kkiaprj.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @GetMapping("/schedule")
    public void schedule(Model model){

        List<GameSchedule> gameSchedules = gameService.getSchedule();
        model.addAttribute("schedules", gameSchedules);
    };
}
