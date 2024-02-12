package com.example.Lesson_26_kun_uz1.Entity;

import com.example.Lesson_26_kun_uz1.Enums.LikeStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "article_comment_like")
@Setter
@Getter
public class ArticleCommentLikeEntity extends BaseEntity {
//    id,profile_id,comment_id,created_date,status,

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Column(name = "profile_id")
    private Integer profileID;


    @Column(name = "comment_id")
    private Long commentID;

    @ManyToOne
    @JoinColumn(name = "comment_id",insertable = false,updatable = false)
    private ArticleCommentEntity comment;


    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

//    @Column(name = "create_Date")
//    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LikeStatus status;


}
