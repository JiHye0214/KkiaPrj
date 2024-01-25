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

    // 맛집 글 상세에서는 어떤 유저가 작성했는지, 어떤 맛집 항목들이 있는지, 어떤 댓글들이 달려있는지 필요
    // + 어떤 유저가 어떤 맛집 글에 저장을 눌렀는지 저장, 조회 필요
    // 따라서 해당 타입들의 데이터 가져와야 함

    @ManyToOne(optional = false)// food 테이블에 user_id 라는 외래키 생김 + food 기준으로 inner join (user_id 는 null 값 허용 X)
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL) // food 테이블의 데이터 삭제 시 해당 데이터 참조하고 있던 food_item 의 데이터들도 삭제
    @JoinColumn(name = "food_id") // food_item 테이블의 food_id 라는 외래키와 연결됨 (부모 : food, 자식 : food_item)
    @ToString.Exclude
    private List<FoodItem> foodItems = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL) // food 테이블의 데이터 삭제 시 해당 데이터 참조하고 있던 food_comment 의 데이터들도 삭제
    @JoinColumn(name = "food_id") // food_comment 테이블의 food_id 라는 외래키와 연결됨 (부모 : food, 자식 : food_comment)
    @ToString.Exclude
    private List<FoodComment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL) // food 테이블의 데이터 삭제 시 해당 데이터 참조하고 있던 food_save 의 데이터들도 삭제
    @JoinColumn(name = "food_id") // food_save 테이블의 food_id 라는 외래키와 연결됨 (부모 : food, 자식 : food_save)
    @ToString.Exclude
    private List<FoodSave> foodSaves = new ArrayList<>();

}
