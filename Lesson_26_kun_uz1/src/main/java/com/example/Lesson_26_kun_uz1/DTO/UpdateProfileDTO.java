package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProfileDTO {
    private ProfileRole role;
    private ProfileStatus status;
    private String email;
    private String name;
}
