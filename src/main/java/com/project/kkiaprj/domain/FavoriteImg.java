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
@Entity(name = "favoriteimg")
public class FavoriteImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "favorite_id", nullable = false)
    private Long favorite;

    @Column(nullable = false)
    private String sourcename;

    @Column(nullable = false)
    private String filename;

    @Transient
    private boolean isImage;

}