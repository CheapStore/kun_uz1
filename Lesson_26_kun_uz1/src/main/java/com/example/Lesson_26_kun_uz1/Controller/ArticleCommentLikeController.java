package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleCommentLikeService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articleCommentLike")
public class ArticleCommentLikeController {
    @Autowired
    private ArticleCommentLikeService service;


    @PostMapping("/any/like/{id}")
    private ResponseEntity<?> like(@PathVariable(value = "id") Integer commentId) {
        Integer profileID = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(service.like(commentId, profileID));

    }

    @PostMapping("/any/disLike/{id}")
    private ResponseEntity<?> disLike(@PathVariable(value = "id") Integer commentID) {
    Integer profileID = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(service.disLike(commentID,profileID));
    }


    @PostMapping("/any/remove/{id}")
    private ResponseEntity<?> remove(@PathVariable(value = "id") Integer commentID) {
        Integer profileID = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(service.remove(commentID,profileID));
    }



}
