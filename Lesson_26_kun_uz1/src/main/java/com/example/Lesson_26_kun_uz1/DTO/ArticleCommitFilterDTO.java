package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleCommitFilterDTO {
//    id,created_date_from,created_date_to,profile_id,article_id

    private Integer id;
    private LocalDate createDateFrom;
    private LocalDate createDateTo;
    private Integer profileID;
    private String articleID;
}
