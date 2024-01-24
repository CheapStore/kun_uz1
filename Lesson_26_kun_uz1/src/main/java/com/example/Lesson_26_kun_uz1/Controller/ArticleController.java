package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.ArticleDTO;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    public ArticleService articleService;

    @PostMapping("/create")
    public ResponseEntity<ArticleDTO>create(@RequestBody ArticleDTO articleDTO){
        return  ResponseEntity.ok(articleService.create(articleDTO));

    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id")Integer id,
                                          @RequestBody ArticleDTO articleDTO){

        return  ResponseEntity.ok(articleService.update(id,articleDTO));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable(value = "id")Integer id){
        return  ResponseEntity.ok(articleService.delete(id));
    }

    @GetMapping("/language")
    public ResponseEntity<List<Object>>getList(@RequestParam(value = "language")String language){
       return ResponseEntity.ok(articleService.getlist(language));
    }


}
