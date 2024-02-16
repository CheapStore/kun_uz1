package com.example.Lesson_26_kun_uz1.DTO;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class JwtDTO
{
    private Integer id;
    private ProfileRole role;
    private String email;

    public JwtDTO(Integer id) {
        this.id = id;
    }

    public JwtDTO( String email,ProfileRole role) {
        this.role = role;
        this.email = email;
    }

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }
}
