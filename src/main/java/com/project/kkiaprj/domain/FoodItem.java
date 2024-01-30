package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "food_item")
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String restaurantName;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String address;

    private double lat;

    private double lng;

    @ManyToOne(optional = false)
    @JoinColumn(name = "foodId")
    @ToString.Exclude
    private Food food;

}
