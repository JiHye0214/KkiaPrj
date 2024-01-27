package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicInsert
@DynamicUpdate
@Entity(name = "favorite")
public class Favorite extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String title;

    @Column(length = 10, nullable = false)
    private String playerName;

    @Column(length = 10, nullable = false)
    private String playerNum;

    @ColumnDefault(value = "0")
    private long likeCnt;

    @ColumnDefault(value = "0")
    private long viewCnt;

    @Column(length = 10)
    @ColumnDefault(value = "'false'")
    private String isLikeClicked;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favoriteId")
    @ToString.Exclude
    private List<FavoriteImg> favoriteImgs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favoriteId")
    @ToString.Exclude
    private List<FavoriteComment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "favoriteId")
    @ToString.Exclude
    private List<FavoriteLike> favoriteLikes = new ArrayList<>();

}
