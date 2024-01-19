package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "postimg")
public class PostImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long post;

    @Column(nullable = false)
    private String sourcename;

    @Column(nullable = false)
    private String filename;

    @Transient
    private boolean isImage;

}








