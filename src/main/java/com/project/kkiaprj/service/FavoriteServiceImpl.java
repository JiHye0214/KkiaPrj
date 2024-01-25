package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Favorite;
import com.project.kkiaprj.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    // 최애 글 목록 조회 (페이징)
    @Override
    public List<Favorite> list(Integer page, Model model) {
        if (page < 1) page = 1;

        int pagesPerSection = 5;
        int rowsPerPage = 10;

        Page<Favorite> pagedFavorite = favoriteRepository.findAll(PageRequest.of(page - 1, rowsPerPage, Sort.by(Sort.Order.desc("id"))));

        long totalLength = pagedFavorite.getTotalElements();
        int totalPage = pagedFavorite.getTotalPages();

        int startPage = 0;
        int endPage = 0;

        List<Favorite> lists = new ArrayList<>();

        if (totalLength > 0) {
            if (page > totalPage) page = totalPage;

            startPage = (((page - 1) / pagesPerSection) * pagesPerSection) + 1;
            endPage = startPage + pagesPerSection - 1;
            if (endPage > totalPage) endPage = totalPage;

            lists = pagedFavorite.getContent();
            model.addAttribute("lists", lists);
        } else {
            page = 0;
        }

        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);

        model.addAttribute("url", U.getRequest().getRequestURI());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return lists;
    }

    // 최애 글 상세 조회 (조회수 증가X)
    @Override
    public Favorite detailById(Long id) {
        return favoriteRepository.findById(id).orElse(null);
    }

    // 최애 글 상세 조회 (조회수 증가O)
    @Override
    @Transactional
    public Favorite detail(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElse(null);

        if (favorite != null) {
            favorite.setViewCnt(favorite.getViewCnt() + 1);
            favoriteRepository.saveAndFlush(favorite);
        }

        return favorite;
    }

}
