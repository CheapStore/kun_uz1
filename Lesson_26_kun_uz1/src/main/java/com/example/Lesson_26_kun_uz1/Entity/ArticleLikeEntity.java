package com.example.Lesson_26_kun_uz1.Entity;

import com.example.Lesson_26_kun_uz1.Enums.LikeStatus;
import jakarta.persistence.*;
import jakarta.websocket.OnMessage;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "like_table")
@Setter
@Getter
public class ArticleLikeEntity {
//    id,created_date,status,


//    ,profile_id,article_id,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LikeStatus status;



    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;


    @Column(name = "article_id")
    private String articleId;

    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;




}
