package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Post;
import com.project.kkiaprj.domain.PostComment;
import com.project.kkiaprj.repository.PostCommentRepository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PostService {

    List<Post> getPostList(Integer page, String sq, Model model);

    Post getPost(Long id);

    int writePost(Post post, Map<String, MultipartFile> file);

    int updatePost(Post post, Map<String, MultipartFile> file, Long[] delFile);

    int deletePost(Post post);

    // 댓글 작성
    int writePostComment(Long listItemId, PostComment postComment);
    // 댓글 삭제
    int deletePostComment(PostComment postComment);
}
