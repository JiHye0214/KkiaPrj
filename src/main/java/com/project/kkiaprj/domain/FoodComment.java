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
    @ManyToOne(optional = false) // food_comment 테이블에 userId 라는 외래키 생김 + food_comment 기준으로 inner join (userId 는 null 값 허용 X)
    @JoinColumn(name = "userId") // userId 라는 외래키 생김. 설정 안하면 디폴트는 user_id
    @ToString.Exclude
    private User user;

    @Column(name = "foodId") // food_comment 테이블에 foodId 라는 외래키 생김
    @ToString.Exclude
    private Long foodId;

}
