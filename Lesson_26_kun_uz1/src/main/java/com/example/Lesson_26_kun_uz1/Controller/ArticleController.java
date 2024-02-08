package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.CreateArticleDTO;
import com.example.Lesson_26_kun_uz1.DTO.UpdateArticleDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping("/moder")
    public ResponseEntity<?> create(@RequestBody CreateArticleDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, profileId));
    }

    @PutMapping("/moder/{id}")
    public ResponseEntity<String> update(@RequestBody UpdateArticleDTO dto,
                                         @PathVariable(value = "id") String id,
                                         HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id, dto));
    }


}
