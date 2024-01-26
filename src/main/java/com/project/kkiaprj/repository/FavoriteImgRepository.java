package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.FavoriteImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteImgRepository extends JpaRepository<FavoriteImg, Long> {

    List<FavoriteImg> findByFavoriteId(Long favoriteId);

}
