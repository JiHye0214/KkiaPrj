package com.project.kkiaprj.controller;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Post;
import com.project.kkiaprj.domain.PostComment;
import com.project.kkiaprj.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/community/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/list")
    public String postList(@RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                           @RequestParam(name = "sq", required = false, defaultValue = "") String sq,
                           HttpServletRequest request,
                           Model model){
        String uri = U.getRequest().getRequestURI();
        request.getSession().setAttribute("prevPage", uri);

        postService.getPostList(page, sq, model);
        return "community/post/list";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("listItem", postService.getPost(id));
        model.addAttribute("page", "post");

        return "community/post/detail";
    }

    @GetMapping("/write")
    public void postWrite(){};

    @GetMapping("/update/{id}")
    public String postUpdate(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "community/post/update";
    }

    // 검색 post
    @PostMapping("/search")
    public String marketSearch(String sq,
                               RedirectAttributes redirectAttrs) {
        redirectAttrs.addAttribute("sq", sq);
        return "redirect:/community/post/list";
    }

    @PostMapping("/write")
    public String postWrite(Post post,
                            @RequestParam Map<String, MultipartFile> file,
                            Model model) {
        model.addAttribute("result", postService.writePost(post, file));
        model.addAttribute("action", "작성");
        return "community/post/success";
    }

    @PostMapping("/update")
    public String postUpdate(Post post,
                             @RequestParam Map<String, MultipartFile> file,
                             Long[] delfile,
                             Model model) {
        model.addAttribute("result", postService.updatePost(post, file, delfile));
        model.addAttribute("action", "수정");
        return "community/post/success";
    }

    @PostMapping("/delete")
    private String deletePost(Post post, Model model) {
        model.addAttribute("result", postService.deletePost(post));
        model.addAttribute("action", "삭제");
        return "community/post/success";
    }

    //---------------------------------------------------------------------------------------------------
    // 댓글 작성
    @PostMapping("/cmtWrite")
    private String writePostComment(Long listItemId, PostComment postComment, Model model) {
        model.addAttribute("result", postService.writePostComment(listItemId, postComment));
        model.addAttribute("action", "댓글 작성");
        return "community/post/success";
    }

    // 댓글 삭제
    @PostMapping("/cmtDelete")
    private String deleteComment(PostComment postComment, Model model) {
        model.addAttribute("result", postService.deletePostComment(postComment));
        model.addAttribute("action", "댓글 삭제");
        return "community/post/success";
    }
}
