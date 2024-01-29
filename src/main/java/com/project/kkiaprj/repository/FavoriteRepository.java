package com.project.kkiaprj.repository;

import com.project.kkiaprj.domain.Favorite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    Page<Favorite> findByPlayerNameContainsOrPlayerNumContains(String sq1, String sq2, Pageable pageable);

    List<Favorite> findTop5ByOrderByLikeCntDesc();

}
