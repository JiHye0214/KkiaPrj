package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "food")
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String title;

    @Column(length = 5, nullable = false)
    private String region;

    @ColumnDefault(value = "0")
    private long saveCnt;

    @ColumnDefault(value = "0")
    private long viewCnt;

    // 맛집 글 상세에서는 어떤 유저가 작성했는지, 어떤 맛집 항목들이 있는지, 어떤 댓글들이 달려있는지 필요
    // + 어떤 유저가 어떤 맛집 글에 저장을 눌렀는지 정보 필요
    // 따라서 해당 타입들의 데이터 가져와야 함

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FoodItem> foodItems = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FoodComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<FoodSave> foodSaves = new ArrayList<>();

}
