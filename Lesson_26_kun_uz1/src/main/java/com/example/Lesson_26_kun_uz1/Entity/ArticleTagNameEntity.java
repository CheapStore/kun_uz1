package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article_tags")
@Setter
@Getter
public class ArticleTagNameEntity extends BaseEntity{


    @Column(name = "article_id")
    private String articleId;

    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false,updatable = false)
    private ArticleEntity article;

    @Column(name = "tags_id")
    private Long tagsId;
    @ManyToOne
    @JoinColumn(name = "tags_id",insertable = false,updatable = false)
    private TagNameEntity types;
}
