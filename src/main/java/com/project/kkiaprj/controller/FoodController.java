package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Food;
import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.service.FoodCommentService;
import com.project.kkiaprj.service.FoodService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/community/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodCommentService foodCommentService;

    List<String> regions = List.of("고척", "광주", "대구", "대전", "부산", "수원", "인천", "잠실", "창원");

    // 맛집 글 목록 페이지
    @GetMapping("/list")
    public String list(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page
            , @RequestParam(name = "region", required = false, defaultValue = "") String region
            , HttpServletRequest request
            , Model model
    ) {
        String uri = U.getRequest().getRequestURI();
        request.getSession().setAttribute("prevPage", uri);

        boolean isValidRegion = false;
        for (String r : regions) {
            if (region.equals(r) || region.isEmpty()) {
                isValidRegion = true;
            }
        }

        if (isValidRegion) {
            foodService.list(page, region, model);
            return "community/food/list";
        } else {
            return "redirect:/community/food/list";
        }
    }

    // 지역 필터
    @PostMapping("/regionSelect")
    public String regionSelect(
            String region
            , RedirectAttributes redirectAttr
    ) {
        redirectAttr.addAttribute("region", region);
        return "redirect:/community/food/list";
    }

    // 맛집 글 상세 페이지
    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable(name = "id") Long id
            , Model model
    ) {
        model.addAttribute("listItem", foodService.detail(id));
        return "community/food/detail";
    }

    // 맛집 글 작성 페이지
    @GetMapping("/write")
    public void write() {
    }

    // 맛집 글 작성
    @PostMapping("write")
    public String wirteOk(
            Food food
            , @RequestParam List<String> restaurantName
            , @RequestParam List<String> content
            , @RequestParam List<String> address
            , @RequestParam List<Double> lat
            , @RequestParam List<Double> lng
            , String action
            , Model model
    ) {
        model.addAttribute("result", foodService.write(food, restaurantName, content, address, lat, lng));
        model.addAttribute("action", action);
        return "community/food/success";
    }

    // 맛집 글 수정 페이지
    @GetMapping("/update/{id}")
    public String update(
            @PathVariable(name = "id") Long id
            , Model model
    ) {
        Food food = foodService.detailById(id);
        model.addAttribute("food", food);
        return "community/food/update";
    }

    // 맛집 글 수정
    @PostMapping("/update")
    public String updateOk(
            Food food
            , @RequestParam List<String> restaurantName
            , @RequestParam List<String> content
            , @RequestParam List<String> address
            , @RequestParam List<Double> lat
            , @RequestParam List<Double> lng
            , String action
            , Model model
    ) {
        model.addAttribute("result", foodService.update(food, restaurantName, content, address, lat, lng));
        model.addAttribute("action", action);
        return "community/food/success";
    }

    // 맛집 글 삭제
    @PostMapping("/delete")
    public String deleteOk(
            Long id
            , String action
            , Model model
    ) {
        model.addAttribute("result", foodService.delete(id));
        model.addAttribute("action", action);
        return "community/food/success";
    }

    // ----------------------------------------------------------------------------------------------------

    // 댓글 작성
    @PostMapping("/cmtWrite")
    public String cmtWriteOk(
            FoodComment foodComment
            , Long foodId
            , String action
            , Model model
    ) {
        model.addAttribute("result", foodCommentService.write(foodComment, foodId));
        model.addAttribute("foodId", foodId);
        model.addAttribute("action", action);
        return "community/food/success";
    }

    // 댓글 삭제
    @PostMapping("/cmtDelete")
    public String cmtDeleteOk(
            Long id
            , Long foodId
            , String action
            , Model model
    ) {
        model.addAttribute("result", foodCommentService.delete(id));
        model.addAttribute("foodId", foodId);
        model.addAttribute("action", action);
        return "community/food/success";
    }

}
