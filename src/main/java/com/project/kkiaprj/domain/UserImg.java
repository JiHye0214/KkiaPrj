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
@Entity(name = "user_img")
public class UserImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false) // 작성자 필수임
    @JoinColumn(name = "userId") // user에는 안 적어도 되는구나??!!
    private User user;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String sourceName;
}
