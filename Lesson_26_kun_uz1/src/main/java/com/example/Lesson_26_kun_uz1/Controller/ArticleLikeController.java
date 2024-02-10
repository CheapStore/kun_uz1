package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Service.ArticleLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleLikeController {
    @Autowired
    public ArticleLikeService service;

   @PutMapping("/Like/{id}")
    public ResponseEntity<?>like(@PathVariable(value = "id")String articleID,
                                 @RequestParam(value = "profileID")Integer profileId){
       return ResponseEntity.ok(service.addLike(articleID,profileId));
   }


    @PutMapping("/DisLike/{id}")
    public ResponseEntity<?>disLike(@PathVariable(value = "id")String articleID,
                                    @RequestParam(value = "profileID")Integer profileId){
        return ResponseEntity.ok(service.addDisLike(articleID,profileId));
    }
}
