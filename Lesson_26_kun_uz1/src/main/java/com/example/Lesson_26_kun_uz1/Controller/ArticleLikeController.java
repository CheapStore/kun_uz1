package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleLikeService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleLikeController {
    @Autowired
    public ArticleLikeService service;

   @PostMapping("/any/Like/{id}")
    public ResponseEntity<?>like(@PathVariable(value = "id")String articleID,
                                 HttpServletRequest request){
       Integer profileId = HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN,
               ProfileRole.PUBLISHER,ProfileRole.USER,ProfileRole.MODERATOR);
       return ResponseEntity.ok(service.addLike(articleID,profileId));
   }


    @PostMapping("/any/DisLike/{id}")
    public ResponseEntity<?>disLike(@PathVariable(value = "id")String articleID,
                                    HttpServletRequest request ){
       Integer profileId = HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN,
                ProfileRole.PUBLISHER,ProfileRole.USER,ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.addDisLike(articleID,profileId));
    }
}
