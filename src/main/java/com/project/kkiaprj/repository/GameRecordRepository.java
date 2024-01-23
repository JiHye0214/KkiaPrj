package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRecordRepository extends JpaRepository<GameRecord, Long> {
    List<GameRecord> findByUserId(Long userId);
    GameRecord findByUserIdAndRecordDate(Long userId, String recordDate);
}
