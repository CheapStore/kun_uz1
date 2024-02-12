package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article_coment")
@Setter
@Getter
public class ArticleCommentEntity extends BaseEntity {

//    id,created_date,update_date,profile_id,content,article_id,reply_id,visible

    @Column(name = "content")
    private String content;

    @Column(name = "article_id")
    private String articleId;

    @Column(name = "profile_id")
    private Integer profileId;

    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;

}
