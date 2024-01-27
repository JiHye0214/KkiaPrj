package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String name;   // 회원 이름

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonFormat(timezone = "Asia/Seoul")
    private LocalDate birth;

    private String gender;

    // 각 게시판의 작성ㆍ수정 페이지, 댓글에서는 유저의 이미지 필요
    // 마이페이지의 커뮤니티에서는 유저가 작성한 글, 작성한 댓글(마켓, 맛집, 최애, 자유) 필요
    // + 저장 맛집, 좋아요 최애 정보 필요
    // 따라서 해당 타입들의 데이터 가져와야 함

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userImgId") // user 테이블에 userImgId 라는 외래키 생김. user_img 테이블과 연결 (부모 : user_img, 자식 : user)
    @ToString.Exclude
    private UserImg userImg;

    @OneToMany(cascade = CascadeType.ALL) // user 테이블의 데이터 삭제 시 해당 데이터 참조하고 있던 food_save 의 데이터들도 삭제
    @JoinColumn(name = "userId") // food_save 테이블의 userId 라는 외래키와 연결됨 (부모 : user, 자식 : food_save)
    @ToString.Exclude
    private List<FoodSave> foodSaves = new ArrayList<>();

}
