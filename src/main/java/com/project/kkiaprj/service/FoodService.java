package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Food;
import org.springframework.ui.Model;

import java.util.List;

public interface FoodService {

    // 맛집 글 목록 조회 (페이징)
    List<Food> list(Integer page, String sq, Model model);

    // 특정 맛집 글 saveCnt 변경 (저장 추가 시 num : 1 / 저장 해제 시 num : -1)
    int changeSaveCnt(Long num, Long foodId);

    // 맛집 글 상세 조회 (조회수 증가X)
    Food detailById(Long id);

    // 맛집 글 상세 조회 (조회수 증가O)
    Food detail(Long id, Model model);

    // 맛집 글 작성
    int write(Food food
            , List<String> restaurantName
            , List<String> content
            , List<String> address
            , List<Double> lat
            , List<Double> lng
    );

    // 맛집 글 수정
    int update(Food food
            , List<String> restaurantName
            , List<String> content
            , List<String> address
            , List<Double> lat
            , List<Double> lng
    );

    // 맛집 글 삭제
    int delete(Long id);

}
