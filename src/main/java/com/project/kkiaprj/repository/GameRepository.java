package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.GameSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameSchedule, Long> {
}
