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
    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "foodId")
    @ToString.Exclude
    private Food food;

}
