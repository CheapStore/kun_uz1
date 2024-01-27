package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.ArticleDTO;
import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleService;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    public ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO articleDTO,
                                             @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(articleService.create(articleDTO)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") Integer id,
                                          @RequestBody ArticleDTO articleDTO,
                                          @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(articleService.update(id, articleDTO)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Integer id,
                                          @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(articleService.delete(id)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/language")
    public ResponseEntity<List<ArticleDTO>> getListLanguage(@RequestParam(value = "language", defaultValue = "UZ") Language language) {
//        return null;
        return ResponseEntity.ok(articleService.getlistLangue(language));
    }

    @GetMapping("/Pagination")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(articleService.pagination(size, page)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }






}
