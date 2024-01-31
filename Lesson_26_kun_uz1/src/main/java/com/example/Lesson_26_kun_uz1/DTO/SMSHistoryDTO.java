package com.example.Lesson_26_kun_uz1.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SMSHistoryDTO {

    private Integer profileId;
    private String cod;
    private String reason;
    private Integer id;
    private LocalDateTime createdDate;


}
