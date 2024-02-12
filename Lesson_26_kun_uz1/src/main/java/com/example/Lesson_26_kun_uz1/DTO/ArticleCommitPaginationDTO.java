package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleCommitPaginationDTO {
    private Integer commitId;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer profileID;
    private String profileName;
    private String profileSurname;
    private String content;
    private String articleID;
    private String title;
}
