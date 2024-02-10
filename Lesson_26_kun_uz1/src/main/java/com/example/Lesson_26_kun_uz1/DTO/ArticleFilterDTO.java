package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ArticleFilterDTO {
//    id,title,region_id,category_id,crated_date_from,created_date_to
//            published_date_from,published_date_to,moderator_id,publisher_id,status

    private Integer id;
    private String title;
    private Integer region_id;
    private Integer category_id;
    private LocalDate crated_date_from;
    private LocalDate created_date_to;
    private LocalDate published_date_from;
    private LocalDate published_date_to;
    private Integer moderator_id;
    private Integer publisher_id;
    private ArticleStatus status;

}
