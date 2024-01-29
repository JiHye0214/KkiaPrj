package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "market_talk")
public class MarketTalk extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user; // 쓴 사람

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long recipientId; // 받는 사람

    @Column(name = "roomId")
    @ToString.Exclude
    private Long roomId; // 톡방 id
}