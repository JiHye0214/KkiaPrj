package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Food;
import org.springframework.ui.Model;

import java.util.List;

public interface FoodService {

    // 맛집 글 목록 조회 (페이징)
    List<Food> list(Integer page, String sq, Model model);

    // 맛집 글 상세 조회 (조회수 증가X)
    Food detailById(Long id);

    // 맛집 글 상세 조회 (조회수 증가O)
    Food detail(Long id);

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
