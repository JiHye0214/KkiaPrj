package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "food_comment")
public class FoodComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    // 맛집 글 상세의 댓글에서는 어떤 유저가 작성했는지 필요
    // 따라서 User 타입의 데이터 가져오기
    @ManyToOne(optional = false) // food_comment 테이블에 user_id 라는 외래키 생김 + food_comment 기준으로 inner join (user_id 는 null 값 허용 X)
    @ToString.Exclude
    private User user;

    @Column(name = "food_id") // food_comment 테이블에 food_id 라는 외래키 생김
    @ToString.Exclude
    private Long foodId;

}
