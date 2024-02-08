package com.example.Lesson_26_kun_uz1.Util;

import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Exp.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUTIL {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRolelist) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        if (requiredRolelist.length==0)return id;
        for (ProfileRole requiredRole : requiredRolelist) {
            if (role.equals(requiredRole)) {
                return id;
            }
        }
        throw new ForbiddenException("Method not allowed");


    }

    public static JwtDTO getJWTDTO(HttpServletRequest request, ProfileRole... requiredRolelist) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO jwtDTO = new JwtDTO(id, role);
        for (ProfileRole requiredRole : requiredRolelist) {
            if (role.equals(requiredRole)) {
                return jwtDTO;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }


}
