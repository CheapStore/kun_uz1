package com.example.Lesson_26_kun_uz1.Util;


import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {

    public static CustomUserDetail getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName(); // username
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CustomUserDetail userDetails = (CustomUserDetail) authentication.getPrincipal();
        return userDetails;
    }

}
