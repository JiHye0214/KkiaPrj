package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.MarketTalkList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarketTalkListRepository extends JpaRepository<MarketTalkList, Long> {

    MarketTalkList findByNameIsOrNameIs(String name1, String name2);

    List<MarketTalkList> findByNameStartingWithOrNameEndingWith(Long userId1, Long userId2);

}
