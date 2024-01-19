package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "postrecommend")
public class PostRecommend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // PK

    // Comment:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 추천인 (FK)

    @JsonIgnore
    @Column(name = "post_id")
    private Long post;   // 추천글 (FK)

}
