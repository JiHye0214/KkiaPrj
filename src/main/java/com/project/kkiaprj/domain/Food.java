package com.project.kkiaprj.domain;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "food")
@DynamicInsert   // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String region;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ColumnDefault(value = "0")
    private long saveCnt;

    @ColumnDefault(value = "0")
    private long viewCnt;

//    @Column(columnDefinition = "default 'false'")
//    private String isSaveClicked;

    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 글 작성자 (FK)


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_id")
    @ToString.Exclude
    @Builder.Default
    private List<FoodImg> fileList = new ArrayList<>();

    public void addFiles(FoodImg... files) {
        Collections.addAll(fileList, files);
    }


}