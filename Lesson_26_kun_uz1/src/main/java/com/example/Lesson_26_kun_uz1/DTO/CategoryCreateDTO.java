package com.example.Lesson_26_kun_uz1.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
public class CategoryCreateDTO {
    private Integer orderNumber;
    private String nameUz;
    private String nameRu;
    private String nameEn;
}
