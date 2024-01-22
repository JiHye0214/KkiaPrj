package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.LiveChat;

import java.util.List;

public interface CommunityService {

    // main----------------------------------
    // 채팅 배열 가져오기
    List<LiveChat> getChat();

    // 채팅 쓰기
    int writeChat(LiveChat chat);

    // food----------------------------------
    // favorite----------------------------------
    // post----------------------------------
}
