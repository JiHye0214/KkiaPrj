package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "favorite_comment")
public class FavoriteComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "favoriteId")
    @ToString.Exclude
    private Favorite favorite;
}
