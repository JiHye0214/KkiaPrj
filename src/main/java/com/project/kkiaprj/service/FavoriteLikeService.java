package com.project.kkiaprj.service;

public interface FavoriteLikeService {

    // 좋아요(하트) 여부
    boolean isLikeCheck(Long userId, Long favoriteId);

    // 좋아요 추가
    int insertLike(Long userId, Long favoriteId);

    // 좋아요 삭제
    int deleteLike(Long userId, Long favoriteId);

}
