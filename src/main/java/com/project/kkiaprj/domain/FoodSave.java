package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "food_save")
public class FoodSave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId") // food_save 테이블에 user_id 라는 외래키 생김
    @ToString.Exclude
    private Long userId;

    @Column(name = "foodId") // food_save 테이블에 food_id 라는 외래키 생김
    @ToString.Exclude
    private Long foodId;

}
