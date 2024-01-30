package com.project.kkiaprj.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "live_chat")
public class LiveChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private User user;

}
