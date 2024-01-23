package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.GameSchedule;
import com.project.kkiaprj.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    public List<GameSchedule> getSchedule() {
        return gameRepository.findAll();
    }
}
