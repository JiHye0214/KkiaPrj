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
@Entity(name = "diary")
@DynamicInsert   // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Diary{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String month;

    @Column(nullable = false)
    private String date;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String winCheck;

    // Post:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 글 작성자 (FK)
 }