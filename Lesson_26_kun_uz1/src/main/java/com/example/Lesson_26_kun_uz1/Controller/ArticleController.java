package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.ArticleDTO;
import com.example.Lesson_26_kun_uz1.DTO.CreateArticleDTO;
import com.example.Lesson_26_kun_uz1.DTO.ReturningData;
import com.example.Lesson_26_kun_uz1.DTO.UpdateArticleDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PutMapping("/moder/updateVisible/{id}")
    public ResponseEntity<Boolean> updateVisible(@PathVariable(value = "id") String id,
                                                 HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.updateVisible(id));
    }


    @PutMapping("/pub/updateStatus/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") String id,
                                          HttpServletRequest request) {
        Integer publisherID = HttpRequestUTIL.getProfileId(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.updateStatus(id, publisherID));
    }

    @GetMapping("/ArticleByTypes")
    public ResponseEntity<List<ArticleDTO>> ArticleByTypes(@RequestParam(value = "id") Integer id,
                                                           @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(articleService.ArticleByTypes(id, size));
    }

    @GetMapping("/ArticleSwitchId/{id}")
    public ResponseEntity<List<ArticleDTO>> ArticleSwitchId(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(articleService.ArticleSwitchId(id));
    }


    @GetMapping("/getLanguage/{id}")
    public ResponseEntity<ReturningData> getLanguage(@PathVariable(value = "id") String id,
                                                     @RequestParam(value = "lan", defaultValue = "UZ") Language lan) {
        return ResponseEntity.ok(articleService.getLanguage(id, lan));
    }


}
