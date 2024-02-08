package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter

public class UpdateArticleDTO {
    //    title,description,content,shared_count,image_id, region_id,category_id

    private String title;
    private String description;
    private String content;
    private Integer sharedCount;
    private String imagesId;
    private Integer regionId;
    private Integer categoryId;



}
