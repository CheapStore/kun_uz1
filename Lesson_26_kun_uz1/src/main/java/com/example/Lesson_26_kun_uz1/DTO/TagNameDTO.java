package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class TagNameDTO {

    private Long id;
    private String name;
    private LocalDateTime createdDate;

}
