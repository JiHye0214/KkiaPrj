package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "market_talk_list")
public class MarketTalkList {

    // 톡방 하나
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false)
    private String name; // 그냥 넣어준 거임 insert 때문에

    @OneToMany(mappedBy = "marketTalkList", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<MarketTalk> marketTalks = new ArrayList<>();

}
