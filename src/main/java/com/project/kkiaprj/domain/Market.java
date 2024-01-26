package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity(name = "market")
public class Market extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false)
    private String product;

    @Column(nullable = false)
    private String region;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String content;

    @ColumnDefault(value = "0")
    private long viewCnt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "marketId")
    @ToString.Exclude
    private List<MarketImg> marketImgs = new ArrayList<>(); // 이거는 db에 없네?

}
