package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Favorite;
import com.project.kkiaprj.domain.FavoriteComment;
import com.project.kkiaprj.service.FavoriteCommentService;
import com.project.kkiaprj.service.FavoriteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private FavoriteCommentService favoriteCommentService;

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
        model.addAttribute("page", "favorite");
        return "community/favorite/detail";
    }

    // 최애 글 작성 페이지
    @GetMapping("/write")
    public void write() {
    }

    // 최애 글 작성
    @PostMapping("/write")
    public String writeOk(
            Favorite favorite
            , @RequestParam Map<String, MultipartFile> files
            , Model model
    ) {
        model.addAttribute("result", favoriteService.write(favorite, files));
        model.addAttribute("action", "작성");
        return "community/favorite/success";
    }

    // 최애 글 수정 페이지
    @GetMapping("/update/{id}")
    public String update(
            @PathVariable(name = "id") Long id
            , Model model
    ) {
        model.addAttribute("favorite", favoriteService.detailById(id));
        return "community/favorite/update";
    }

    // 최애 글 수정
    @PostMapping("/update")
    public String updateOk(
            Favorite favorite
            , @RequestParam Map<String, MultipartFile> files
            , Long[] delfile
            , Model model
    ) {
        model.addAttribute("result", favoriteService.update(favorite, files, delfile));
        model.addAttribute("action", "수정");
        return "community/favorite/success";
    }

    // 최애 글 삭제
    @PostMapping("/delete")
    public String deleteOk(
            Long id
            , Model model
    ) {
        model.addAttribute("result", favoriteService.delete(id));
        model.addAttribute("action", "삭제");
        return "community/favorite/success";
    }


    // ----------------------------------------------------------------------------------------------------

    // 댓글 작성
    @PostMapping("/cmtWrite")
    public String cmtWriteOk(
            FavoriteComment favoriteComment
            , Long listItemId
            , Model model
    ) {
        model.addAttribute("result", favoriteCommentService.write(favoriteComment, listItemId));
        model.addAttribute("action", "댓글 작성");
        return "community/favorite/success";
    }

    // 댓글 삭제
    @PostMapping("/cmtDelete")
    public String cmtDeleteOk(
            Long id
            , Model model
    ) {
        model.addAttribute("result", favoriteCommentService.delete(id));
        model.addAttribute("action", "댓글 삭제");
        return "community/favorite/success";
    }

}
