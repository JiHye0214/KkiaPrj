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

    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "favoriteId")
    @ToString.Exclude
    private Favorite favorite;

}
