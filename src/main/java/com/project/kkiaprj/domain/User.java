package com.project.kkiaprj.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Entity(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String loginId;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String name;   // 회원 이름

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonFormat(timezone = "Asia/Seoul")
    private LocalDate birth;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private UserImg userImg;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @ToString.Exclude
//    @Builder.Default
//    @JsonIgnore
//    private List<Authority> authorities = new ArrayList<>();
//
//    public void addAuthority(Authority... authorities){
//        Collections.addAll(this.authorities, authorities);
//    }
}