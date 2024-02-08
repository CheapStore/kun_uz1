package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount;
    private String imagesId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Integer viewCount;
    private ArticleStatus status;

    private Integer regionId;
    private Integer categoryId;
    private Integer moderatorId;
    private Integer publishedId;


}
