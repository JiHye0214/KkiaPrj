package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.Food;
import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.FoodCommentRepository;
import com.project.kkiaprj.repository.FoodRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodCommentRepository foodCommentRepository;

    // 맛집 글 댓글 작성
    @Override
    public int write(FoodComment foodComment, Long foodId) {
        int result = 0;

        User user = U.getLoggedUser();
        user = userRepository.findById(user.getId()).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);

        if (user != null) {
            foodComment.setUser(user);
            foodComment.setFood(food);
            foodCommentRepository.saveAndFlush(foodComment);

            result = 1;
        }

        return result;
    }

    // 맛집 글 댓글 삭제
    @Override
    public int delete(Long id) {
        int result = 0;
        FoodComment foodComment = foodCommentRepository.findById(id).orElse(null);

        if (foodComment != null) {
            foodCommentRepository.delete(foodComment);
            result = 1;
        }

        return result;
    }

}
