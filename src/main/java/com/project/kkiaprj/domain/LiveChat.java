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

    @ManyToOne(optional = false) // 작성자 필수임
    @JoinColumn(name = "userId") // user에는 안 적어도 되는구나??!!
    private User user;

    @Column(nullable = false)
    private String content;
}
