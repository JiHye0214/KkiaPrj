package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.service.FavoriteService;
import com.project.kkiaprj.service.FoodService;
import com.project.kkiaprj.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/community/favorite")
public class FavoriteController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;

    // 최애 글 목록 페이지
    @GetMapping("/list")
    public String list(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page
            ,@RequestParam(name = "sq", required = false, defaultValue = "") String sq
            , HttpServletRequest request
            , Model model
    ) {
        String uri = U.getRequest().getRequestURI();
        request.getSession().setAttribute("prevPage", uri);

        favoriteService.list(page, sq, model);
        return "community/favorite/list";
    }

    // 검색
    @PostMapping("/search")
    public String search(
            String sq
            , RedirectAttributes redirectAttr
    ) {
        redirectAttr.addAttribute("sq", sq);
        return "redirect:/community/favorite/list";
    }

    // 최애 글 상세 페이지
    @GetMapping("/detail/{id}")
    public String detail(
            @PathVariable(name = "id") Long id
            , Model model
    ) {
        model.addAttribute("listItem", favoriteService.detail(id));
        return "community/favorite/detail";
    }

}
