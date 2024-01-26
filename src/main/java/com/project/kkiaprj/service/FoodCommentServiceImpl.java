package com.project.kkiaprj.service;

import com.project.kkiaprj.Util.U;
import com.project.kkiaprj.domain.FoodComment;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.domain.UserImg;
import com.project.kkiaprj.repository.FoodCommentRepository;
import com.project.kkiaprj.repository.UserImgRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserImgRepository userImgRepository;

    @Autowired
    private FoodCommentRepository foodCommentRepository;

    @Override
    public int write(FoodComment foodComment, Long foodId) {
        int result = 0;

        User user = U.getLoggedUser();
        user = userRepository.findById(user.getId()).orElse(null);

        if (user != null) {
            String userImgFileName = userImgRepository.findByUserId(user.getId()).getFileName();

            foodComment.setUser(user);
            foodComment.setFoodId(foodId);
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
