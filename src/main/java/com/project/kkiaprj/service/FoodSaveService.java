package com.project.kkiaprj.service;

public interface FoodSaveService {

    // 저장(별) 여부
    boolean isSaveCheck(Long userId, Long foodId);

    // 저장 추가
    int insertSave(Long userId, Long foodId);

    // 저장 삭제
    int deleteSave(Long userId, Long foodId);

}
