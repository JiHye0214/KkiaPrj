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

    @OneToOne(mappedBy = "userImg") // 작성자 필수임 // 아니 근데 mappedBy는 class 이름이어야 하는 건가?
    private User user;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String sourceName;
}
