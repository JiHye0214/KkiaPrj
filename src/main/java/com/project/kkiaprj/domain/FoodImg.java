package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "foodimg")
public class FoodImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "food_id", nullable = false)
    private Long food;

    @Column(nullable = false)
    private String sourcename;

    @Column(nullable = false)
    private String filename;

    @Transient
    private boolean isImage;

}
