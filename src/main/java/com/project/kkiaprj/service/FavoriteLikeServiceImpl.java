package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.*;
import com.project.kkiaprj.repository.FavoriteLikeRepository;
import com.project.kkiaprj.repository.FavoriteRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteLikeServiceImpl implements FavoriteLikeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteLikeRepository favoriteLikeRepository;

    // 좋아요(하트) 여부
    @Override
    public boolean isLikeCheck(Long userId, Long favoriteId) {
        FavoriteLike favoriteLike = favoriteLikeRepository.findByUserIdAndFavoriteId(userId, favoriteId);
        return (favoriteLike != null);
    }

    // 좋아요 추가
    @Override
    public int insertLike(Long userId, Long favoriteId) {
        int result = 0;

        User user = userRepository.findById(userId).orElse(null);
        Favorite favorite = favoriteRepository.findById(favoriteId).orElse(null);

        if (user != null && favorite != null) {
            FavoriteLike favoriteLike = new FavoriteLike();
            favoriteLike.setUser(user);
            favoriteLike.setFavorite(favorite);

            favoriteLikeRepository.saveAndFlush(favoriteLike);

            result = 1;
        }

        return result;
    }

    // 좋아요 삭제
    @Override
    public int deleteLike(Long userId, Long favoriteId) {
        int result = 0;
        FavoriteLike favoriteLike = favoriteLikeRepository.findByUserIdAndFavoriteId(userId, favoriteId);

        if (favoriteLike != null) {
            favoriteLikeRepository.delete(favoriteLike);
            result = 1;
        }

        return result;
    }

}
