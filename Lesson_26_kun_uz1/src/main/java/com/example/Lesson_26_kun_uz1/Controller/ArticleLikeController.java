package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.Service.ArticleLikeService;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/article")
public class ArticleLikeController {
    @Autowired
    public ArticleLikeService service;

   @PostMapping("/any/Like/{id}")
   @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    public  ResponseEntity<?>like(@PathVariable(value = "id")String articleID){

       Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
       return ResponseEntity.ok(service.addLike(articleID,profileId));

   }


    @PostMapping("/any/DisLike/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    public ResponseEntity<?>disLike(@PathVariable(value = "id")String articleID){
       Integer profileID = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(service.addDisLike(articleID,profileID));
    }

    @PostMapping("/any/remove/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','MODERATOR','PUBLISHER')")
    public ResponseEntity<?> remove(@PathVariable(value = "id")String articleID){
    Integer profileID = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(service.remove(articleID,profileID));

    }
}
