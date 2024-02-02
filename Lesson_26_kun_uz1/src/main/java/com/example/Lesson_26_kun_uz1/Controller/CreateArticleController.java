package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.CreateArticleDTO;
import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.CreateArticleService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class CreateArticleController {
    @Autowired
    private CreateArticleService createArticleService;

    @PostMapping("/moder")
    public ResponseEntity<?>create(@RequestBody CreateArticleDTO dto,
                                   HttpServletRequest request){
        Integer moderatorId = HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        createArticleService.create(dto,moderatorId);
        return null;
    }
}
