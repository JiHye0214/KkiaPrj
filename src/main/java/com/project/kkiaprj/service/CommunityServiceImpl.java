package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.LiveChat;
import com.project.kkiaprj.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    // main----------------------------------
    // 채팅 리스트 가져오기
    @Override
    public List<LiveChat> getChat() {
        return communityRepository.findAll();
    }

    // 채팅 쓰기
    @Override
    public int writeChat(LiveChat chat) {
        communityRepository.save(chat);
        return 1;
    }

    // food----------------------------------
    // favorite----------------------------------
    // post----------------------------------
}
