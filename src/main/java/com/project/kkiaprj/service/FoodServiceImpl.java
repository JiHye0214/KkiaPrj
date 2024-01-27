package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.*;
import com.project.kkiaprj.repository.FoodItemRepository;
import com.project.kkiaprj.repository.FoodRepository;
import com.project.kkiaprj.repository.FoodSaveRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodSaveService foodSaveService;

    // 맛집 글 목록 조회 (페이징)
    @Override
    public List<Food> list(Integer page, String sq, Model model) {
        if (page < 1) page = 1;

        int pagesPerSection = 5;
        int rowsPerPage = 9;

        Page<Food> pagedFood = null;
        if (sq.isEmpty()) {
            pagedFood = foodRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        } else {
            pagedFood = foodRepository.findByRegion(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
        }

        long totalLength = pagedFood.getTotalElements();
        int totalPage = pagedFood.getTotalPages();

        int startPage = 0;
        int endPage = 0;

        String isLoggedIn = null;

        List<Food> lists = new ArrayList<>();

        if (totalLength > 0) {
            // page 가 totalPage 보다 크다면 pagedFood 에 아무것도 안 담겨있으므로 내용물 있는 마지막 페이지 값(totalPage)으로 재검색 필요
            if (page > totalPage) {
                page = totalPage;

                if (sq.isEmpty()) {
                    pagedFood = foodRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                } else {
                    pagedFood = foodRepository.findByRegion(sq, PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));
                }
            }

            startPage = (((page - 1) / pagesPerSection) * pagesPerSection) + 1;
            endPage = startPage + pagesPerSection - 1;
            if (endPage > totalPage) endPage = totalPage;

            lists = pagedFood.getContent();
            model.addAttribute("lists", lists);

            // -------------------- 저장된 맛집 여부 체크 --------------------

            // 클릭되어있는 별인지 아닌지 확인할 isSaveClicked
            int itemCnt = pagedFood.getNumberOfElements();
            List<String> isSaveClicked = new ArrayList<>();
            for (int i = 0; i < itemCnt; i++) {
                isSaveClicked.add("false");
            }

            // 로그인 상태 확인
            String user = "" + SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            // 로그인 한 상태라면
            if(!user.equals("anonymousUser")) {
                isLoggedIn = "true";
                Long userId = U.getLoggedUser().getId();

                for (int i = 0; i < itemCnt; i++) {
                    boolean isSaveCheck = foodSaveService.isSaveCheck(userId, lists.get(i).getId());
                    if (isSaveCheck) {
                        isSaveClicked.set(i, "true");
                    }
                }
            }

            System.out.println("=======================isLoggedIn:" + isLoggedIn);
            System.out.println("=======================isSaveClicked:" + isSaveClicked);

            model.addAttribute("isSaveClicked", isSaveClicked);
        } else {
            page = 0;
        }

        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("sq", sq);
        model.addAttribute("isLoggedIn", isLoggedIn);

        return lists;
    }

    @Override
    public int changeSaveCnt(Long num, Long foodId) {
        int result = 0;
        Food food = foodRepository.findById(foodId).orElse(null);

        if (food != null) {
            food.setSaveCnt(food.getSaveCnt() + num);
            foodRepository.saveAndFlush(food);

            result = 1;
        }

        return result;
    }

    // 맛집 글 상세 조회 (조회수 증가X)
    @Override
    public Food detailById(Long id) {
        return foodRepository.findById(id).orElse(null);
    }

    // 맛집 글 상세 조회 (조회수 증가O)
    @Override
    @Transactional
    public Food detail(Long id) {
        Food food = foodRepository.findById(id).orElse(null);

        if (food != null) {
            food.setViewCnt(food.getViewCnt() + 1);
            foodRepository.saveAndFlush(food);
        }

        return food;
    }

    // 맛집 글 작성
    @Override
    public int write(Food food
            , List<String> restaurantName
            , List<String> content
            , List<String> address
            , List<Double> lat
            , List<Double> lng
    ) {
        List<FoodItem> foodItems = new ArrayList<>();

        for (int i = 0; i < restaurantName.size(); i++) {
            FoodItem foodItem = FoodItem.builder()
                    .restaurantName(restaurantName.get(i))
                    .content(content.get(i))
                    .address(address.get(i))
                    .lat(lat.get(i))
                    .lng(lng.get(i))
                    .build();

            foodItems.add(foodItem);
        }

        int result = 0;

        User user = U.getLoggedUser();
        user = userRepository.findById(user.getId()).orElse(null);

        if (user != null) {
            food.setUser(user);
            food.setFoodItems(foodItems);

            foodRepository.saveAndFlush(food);

            result = 1;
        }

        return result;
    }

    // 맛집 글 수정
    @Override
    public int update(
            Food food
            , List<String> restaurantName
            , List<String> content
            , List<String> address
            , List<Double> lat
            , List<Double> lng
    ) {
        List<FoodItem> foodItems = new ArrayList<>();

        for (int i = 0; i < restaurantName.size(); i++) {
            FoodItem foodItem = FoodItem.builder()
                    .restaurantName(restaurantName.get(i))
                    .content(content.get(i))
                    .address(address.get(i))
                    .lat(lat.get(i))
                    .lng(lng.get(i))
                    .build();

            foodItems.add(foodItem);
        }

        int result = 0;
        Food prevFood = foodRepository.findById(food.getId()).orElse(null);

        if (prevFood != null) {
            prevFood.setTitle(food.getTitle());
            prevFood.setRegion(food.getRegion());

            // 맛집글의 맛집 항목들은 굳이 기존 게 수정되고 그럴 필요 없음
            // 맛집글의 기존 맛집 항목들 전부 삭제하고 수정한 항목들로 다시 저장
            List<FoodItem> prevFoodItems = foodItemRepository.findByFoodId(prevFood.getId());
            foodItemRepository.deleteAllInBatch(prevFoodItems);
            prevFood.setFoodItems(foodItems);

            foodRepository.saveAndFlush(prevFood); // UPDATE

            result = 1;
        }

        return result;
    }

    // 맛집 글 삭제
    @Override
    public int delete(Long id) {
        int result = 0;
        Food food = foodRepository.findById(id).orElse(null);

        if (food != null) {
            foodRepository.delete(food);
            result = 1;
        }

        return result;
    }

}
