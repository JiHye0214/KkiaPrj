package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "market")
@DynamicInsert   // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Market extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private String region;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ColumnDefault(value = "0")
    private long viewCnt;

    // Post:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 글 작성자 (FK)


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "market_id")
    @ToString.Exclude
    @Builder.Default
    private List<MarketImg> fileList = new ArrayList<>();

    public void addFiles(MarketImg... files) {
        Collections.addAll(fileList, files);
    }


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "market_id")
    @ToString.Exclude
    @Builder.Default
    private List<MarketComment> marketComments = new ArrayList<>();

    public void addComments(MarketComment... marketComments){
        Collections.addAll(this.marketComments, marketComments);
    }

}