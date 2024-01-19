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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "post")
@DynamicInsert   // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @ColumnDefault(value = "0")
    private long recommendCnt;

    @ColumnDefault(value = "0")
    private long viewCnt;

    // Post:User = N:1
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User user;   // 글 작성자 (FK)

    // 첨부파일
    // Post:File = 1:N
    @OneToMany(cascade = CascadeType.ALL) // cascade = CascadeType.ALL : 삭제등의 동작 발생시 child 도 함께 삭제
    @JoinColumn(name = "post_id")   // 중간테이블 없애고 post_id 로 join 수행
    @ToString.Exclude
    @Builder.Default
    private List<PostImg> fileList = new ArrayList<>();

    public void addFiles(PostImg... files) {
        Collections.addAll(fileList, files);
    }

    // Post:Comment = 1:N
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<PostComment> postcomments = new ArrayList<>();

    public void addComments(PostComment... comments){
        Collections.addAll(this.postcomments, comments);
    }

}









