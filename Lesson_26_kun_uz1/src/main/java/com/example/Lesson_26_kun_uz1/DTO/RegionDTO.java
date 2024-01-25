package com.example.Lesson_26_kun_uz1.DTO;


import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegionDTO {

    private Integer id;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
    private Integer orderNumber;
    private String nameUz;
    private String name;
    private String nameRu;
    private String nameEn;

}
