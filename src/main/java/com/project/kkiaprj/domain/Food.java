package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Entity(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 5, nullable = false)
    private String region;

    @ColumnDefault(value = "0")
    private long saveCnt;

    @ColumnDefault(value = "0")
    private long viewCnt;

    @Column(length = 10)
    @ColumnDefault(value = "'false'")
    private String isSaveClicked;

    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    @ToString.Exclude
//    @Builder.Default
    private List<FoodItem> foodItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    @ToString.Exclude
//    @Builder.Default
    private List<FoodComment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
//    @Builder.Default
    private List<FoodSave> foodSaves = new ArrayList<>();

}
