package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.CreateCommentDTO;
import com.example.Lesson_26_kun_uz1.Service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleComment")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;


    @PostMapping("/create")
    public ResponseEntity<?>create(@RequestBody CreateCommentDTO dto){
        return ResponseEntity.ok(articleCommentService.create(dto));
    }





}
