package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.LiveChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<LiveChat, Long> {


}
