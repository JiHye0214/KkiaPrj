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
@Entity(name = "marketimg")
public class MarketImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "market_id", nullable = false)
    private Long market;

    @Column(nullable = false)
    private String sourcename;

    @Column(nullable = false)
    private String filename;

    @Transient
    private boolean isImage;

}