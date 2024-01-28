package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FavoriteLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteLikeRepository extends JpaRepository<FavoriteLike, Long> {

    FavoriteLike findByUserIdAndFavoriteId(Long userId, Long favoriteId);

}
