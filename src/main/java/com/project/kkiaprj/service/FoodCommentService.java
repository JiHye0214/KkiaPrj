package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.FoodComment;

public interface FoodCommentService {

    // 댓글 작성
    int write(FoodComment foodComment, Long foodId);

    // 댓글 삭제
    int delete(Long id);

}
