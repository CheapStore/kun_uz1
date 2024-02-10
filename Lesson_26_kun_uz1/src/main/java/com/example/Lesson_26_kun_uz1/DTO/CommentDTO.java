package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDTO {

//    id,created_date,update_date,profile_id,content,article_id,reply_id,visible

    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String content;
    private String articleId;
    private Integer replyId;
    private Boolean visible;


}
