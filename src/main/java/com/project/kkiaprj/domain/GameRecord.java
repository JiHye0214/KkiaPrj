package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "game_record")
public class GameRecord {
    // 나의 직관 기록

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recordDate;

    @Column(nullable = false)
    private String recordPlace;

    @Column(nullable = false)
    private String recordResult;

    private String recordMemo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

}
