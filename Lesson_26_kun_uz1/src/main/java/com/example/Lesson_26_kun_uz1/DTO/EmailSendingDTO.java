package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class EmailSendingDTO {

    private String email;
    private String sabab;
    private Integer id;
    private LocalDateTime createdDate;
    private String cod;
}
