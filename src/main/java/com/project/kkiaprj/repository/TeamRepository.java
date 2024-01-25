package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByCode(String code);
}
