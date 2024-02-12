package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ArticleCommitGetDTO {
//    id,created_date,update_date,profile(id,name,surname)

    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private Integer profileID;
    private String profileName;
    private String profileSurname;
    private String profileRole;

}
