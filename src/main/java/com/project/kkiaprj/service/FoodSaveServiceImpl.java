package com.project.kkiaprj.service;

import com.project.kkiaprj.domain.Food;
import com.project.kkiaprj.domain.FoodSave;
import com.project.kkiaprj.domain.User;
import com.project.kkiaprj.repository.FoodRepository;
import com.project.kkiaprj.repository.FoodSaveRepository;
import com.project.kkiaprj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodSaveServiceImpl implements FoodSaveService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodSaveRepository foodSaveRepository;

    // 저장(별) 여부
    @Override
    public boolean isSaveCheck(Long userId, Long foodId) {
        FoodSave foodSave = foodSaveRepository.findByUserIdAndFoodId(userId, foodId);
        return (foodSave != null);
    }

    // 저장 추가
    @Override
    public int insertSave(Long userId, Long foodId) {
        int result = 0;

        User user = userRepository.findById(userId).orElse(null);
        Food food = foodRepository.findById(foodId).orElse(null);

        if (user != null && food != null) {
            FoodSave foodSave = new FoodSave();
            foodSave.setUser(user);
            foodSave.setFood(food);

            foodSaveRepository.saveAndFlush(foodSave);

            result = 1;
        }

        return result;
    }

    // 저장 삭제
    @Override
    public int deleteSave(Long userId, Long foodId) {
        int result = 0;
        FoodSave foodSave = foodSaveRepository.findByUserIdAndFoodId(userId, foodId);

        if (foodSave != null) {
            foodSaveRepository.delete(foodSave);
            result = 1;
        }

        return result;
    }

}
