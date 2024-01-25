package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@Entity(name = "favorite_like")
public class FavoriteLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    @ToString.Exclude
    private Long userId;

    @Column(name = "favorite_id")
    @ToString.Exclude
    private Long favoriteId;

}
