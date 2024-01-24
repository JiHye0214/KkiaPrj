package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.GamePlayer;
import com.project.kkiaprj.domain.GameSchedule;

import java.util.List;

public interface GameService {

    List<GameSchedule> getSchedule();
    List<GamePlayer> getPlayer();
}
