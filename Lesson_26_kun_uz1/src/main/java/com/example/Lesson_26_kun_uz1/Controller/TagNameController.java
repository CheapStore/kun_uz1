package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.TagNameDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.TagNameService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tag")
public class TagNameController {
    @Autowired
    private TagNameService tagNameService;

    @PostMapping("/adm")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TagNameDTO> create(@RequestBody TagNameDTO dto,
                                             HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(tagNameService.create(dto));
    }
}
