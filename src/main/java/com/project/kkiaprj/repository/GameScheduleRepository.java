package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.GameSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameScheduleRepository extends JpaRepository<GameSchedule, Long> {
}
