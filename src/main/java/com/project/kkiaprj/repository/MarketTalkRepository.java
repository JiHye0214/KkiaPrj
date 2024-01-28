package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.MarketTalk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTalkRepository extends JpaRepository<MarketTalk, Long> {
    List<MarketTalk> findByWriterIdAndUserIdOrUserId(Long writerId, Long userId, Long writerId2);

}
