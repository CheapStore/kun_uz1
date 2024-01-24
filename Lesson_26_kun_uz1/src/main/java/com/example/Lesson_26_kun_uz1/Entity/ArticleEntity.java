package com.example.Lesson_26_kun_uz1.Entity;

import com.example.Lesson_26_kun_uz1.Enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@Table(name = "article")
public class ArticleEntity extends BaseEntity {


    @Enumerated(value = EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Column(name = "article_name")
    private String articleName;


}
