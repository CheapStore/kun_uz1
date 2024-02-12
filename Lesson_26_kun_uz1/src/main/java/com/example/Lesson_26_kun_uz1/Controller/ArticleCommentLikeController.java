package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleCommentLikeService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
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
    private ResponseEntity<?> like(@PathVariable(value = "id") Integer commentID,
                                   HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.PUBLISHER,
                ProfileRole.ADMIN, ProfileRole.USER, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.like(commentID, profileId));

    }

    @PostMapping("/any/disLike/{id}")
    private ResponseEntity<?> disLike(@PathVariable(value = "id") Integer commentID,
                                   HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.PUBLISHER,
                ProfileRole.ADMIN, ProfileRole.USER, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.disLike(commentID, profileId));

    }


}
