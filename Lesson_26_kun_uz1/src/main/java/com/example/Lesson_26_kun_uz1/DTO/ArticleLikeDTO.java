package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleLikeDTO {
    private Integer id;
    private Integer profile_id;
    private String article_id;
    private LocalDateTime created_date;
    private LikeStatus status;
}
