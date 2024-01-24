package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.GamePlayer;
import com.project.kkiaprj.domain.GameSchedule;
import com.project.kkiaprj.repository.GamePlayerRepository;
import com.project.kkiaprj.repository.GameScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameScheduleRepository gameScheduleRepository;
    @Autowired
    GamePlayerRepository gamePlayerRepository;

    public List<GameSchedule> getSchedule() {
        return gameScheduleRepository.findAll();
    }

    @Override
    public List<GamePlayer> getPlayer() {
        return gamePlayerRepository.findAll();
    }
}
