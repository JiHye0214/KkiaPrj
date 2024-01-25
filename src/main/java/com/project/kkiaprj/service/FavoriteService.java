package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Favorite;
import org.springframework.ui.Model;

import java.util.List;

public interface FavoriteService {

    // 최애 글 목록 조회 (페이징)
    List<Favorite> list(Integer page, Model model);

    // 최애 글 상세 조회 (조회수 증가X)
    Favorite detailById(Long id);

    // 최애 글 상세 조회 (조회수 증가O)
    Favorite detail(Long id);

}
