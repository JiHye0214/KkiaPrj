package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.FoodCommentRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodCommentRepository foodCommentRepository;

    @Override
    public int write(FoodComment foodComment, Long foodId) {
        int result = 0;
        User user = userRepository.findById(1L).orElse(null);

        if (user != null) {
            foodComment.setUser(user);
            foodComment.setFood(foodId);
            foodCommentRepository.saveAndFlush(foodComment);

            result = 1;
        }

        return result;
    }

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
