package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article_coment")
@Setter
@Getter
public class ArticleCommentEntity {
//    (content,article_id,reply_id)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "article_id")
    private String articleId;

    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;

}
