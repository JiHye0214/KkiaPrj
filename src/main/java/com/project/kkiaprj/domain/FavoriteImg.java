package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "favorite_img")
public class FavoriteImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName; // 저장된 파일명 (rename 된 파일명)

    @Column(nullable = false)
    private String sourceName; // 원본 파일명

    @Column(name = "favoriteId")
    @ToString.Exclude
    private Long favoriteId;

}
