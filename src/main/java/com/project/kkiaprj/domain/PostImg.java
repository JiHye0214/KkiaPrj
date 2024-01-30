package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "post_img")
public class PostImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName; // 저장

    @Column(nullable = false)
    private String sourceName; // 원본

    @ManyToOne(optional = false)
    @JoinColumn(name = "postId")
    @ToString.Exclude
    private Post post;

}
