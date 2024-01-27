package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Getter
@Setter
public class FilterDTO {
    private String name;
    private Integer id;
    private String surname;
    private String phone;
    private ProfileRole role;
    private LocalDate fromDate;
    private LocalDate toDate;

}
