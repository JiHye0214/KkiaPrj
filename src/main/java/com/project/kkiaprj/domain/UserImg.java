package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user_img")
public class UserImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String sourceName;

    @Column(name = "userId") // user_img 테이블에 userId 라는 컬럼 생김 (외래키 아니고 그냥 숫자 값 가진 컬럼)
    @ToString.Exclude
    private Long userId;

}
