package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.GamePlayer;
import com.project.kkiaprj.domain.GameSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
}
