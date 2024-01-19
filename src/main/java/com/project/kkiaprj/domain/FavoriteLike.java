package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "favoritelike")
public class FavoriteLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // PK

    // Comment:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 저장인 (FK)

    @JsonIgnore
    @Column(name = "favorite_id")
    private Long favorite;   // 좋아하는 선수 글 (FK)

}