package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Favorite;
import com.project.kkiaprj.domain.FavoriteComment;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.FavoriteCommentRepository;
import com.project.kkiaprj.repository.FavoriteRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteCommentServiceImpl implements FavoriteCommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private FavoriteCommentRepository favoriteCommentRepository;

    // 최애 글 댓글 작성
    @Override
    public int write(FavoriteComment favoriteComment, Long favoriteId) {
        int result = 0;

        User user = U.getLoggedUser();
        user = userRepository.findById(user.getId()).orElse(null);
        Favorite favorite = favoriteRepository.findById(favoriteId).orElse(null);

        if (user != null) {
            favoriteComment.setUser(user);
            favoriteComment.setFavorite(favorite);
            favoriteCommentRepository.saveAndFlush(favoriteComment);

            result = 1;
        }

        return result;
    }

    // 최애 글 댓글 삭제
    @Override
    public int delete(Long id) {
        int result = 0;
        FavoriteComment favoriteComment = favoriteCommentRepository.findById(id).orElse(null);

        if (favoriteComment != null) {
            favoriteCommentRepository.delete(favoriteComment);
            result = 1;
        }

        return result;
    }

}
