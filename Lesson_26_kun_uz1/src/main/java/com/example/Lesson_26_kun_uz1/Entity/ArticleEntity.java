package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity

@Table(name = "article")
public class ArticleEntity extends BaseEntity {



    @Column(name = "Name_Uz")
    private String nameUz;

    @Column(name = "Name_Ru")
    private String nameRu;

    @Column(name = "Name_En")
    private String nameEn;

    @Column(name = "article_name")
    private String articleName;


}
