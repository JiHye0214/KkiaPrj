package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.FavoriteComment;

public interface FavoriteCommentService {

    // 최애 글 댓글 작성
    int write(FavoriteComment favoriteComment, Long favoriteId);

    // 최애 글 댓글 삭제
    int delete(Long id);

}
