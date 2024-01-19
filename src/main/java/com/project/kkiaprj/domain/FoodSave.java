package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "foodsave")
public class FoodSave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // PK

    // Comment:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 저장인 (FK)

    @JsonIgnore
    @Column(name = "food_id")
    private Long post;   // 저장음식글 (FK)

}