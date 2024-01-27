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

    @ManyToOne
    @JoinColumn(name = "userId") // food_save 테이블에 userId 라는 외래키 생김
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "foodId") // food_save 테이블에 foodId 라는 외래키 생김
    @ToString.Exclude
    private Food food;

}
