package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Entity.CategoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter

public class CreateArticleDTO {
//    1. CREATE (Moderator) status(NotPublished)
//            (title,description,content,image_id, region_id,category_id, articleType(array))

    private UUID id;
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
    private Integer moderatorId;
    private Integer puplisherId;
    private Integer cagtegoryId;


}
