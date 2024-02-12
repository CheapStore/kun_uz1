package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentLikeEntity;
import com.example.Lesson_26_kun_uz1.Enums.LikeStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleCommentLikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ArticleCommentLikeService {
    @Autowired
    private ArticleCommentLikeRepository repository;

    public String like(Integer commentID, Integer profileId) {
        Optional<ArticleCommentLikeEntity> optional = repository.findByCommentIDAndProfileID(commentID.longValue(), profileId);
        if (optional.isEmpty()) {
            ArticleCommentLikeEntity entity = new ArticleCommentLikeEntity();
            entity.setCommentID(commentID.longValue());
            entity.setProfileID(profileId);
            entity.setCreatedDate(LocalDateTime.now());
            entity.setStatus(LikeStatus.LIKE);
            repository.save(entity);
            return "Like successful!!!";
        }

        ArticleCommentLikeEntity entity = optional.get();
        if (entity.getStatus()!=null && entity.getStatus().equals(LikeStatus.LIKE)) {
            entity.setStatus(null);
        } else if (entity.getStatus()!=null&&entity.getStatus().equals(LikeStatus.DISLIKE)) {
            entity.setStatus(LikeStatus.LIKE);
        } else {
            entity.setStatus(LikeStatus.LIKE);
        }
        entity.setUpdatedDate(LocalDateTime.now());
        repository.save(entity);

        return "Like successful!!!";

    }

    public String  disLike(Integer commentID, Integer profileId) {
        Optional<ArticleCommentLikeEntity> optional = repository.findByCommentIDAndProfileID(commentID.longValue(), profileId);
        if (optional.isEmpty()) {
            ArticleCommentLikeEntity entity = new ArticleCommentLikeEntity();
            entity.setCommentID(commentID.longValue());
            entity.setProfileID(profileId);
            entity.setCreatedDate(LocalDateTime.now());
            entity.setStatus(LikeStatus.DISLIKE);
            repository.save(entity);
            return "DisLike successful!!!";
        }

        ArticleCommentLikeEntity entity = optional.get();
        if (entity.getStatus()!=null && entity.getStatus().equals(LikeStatus.LIKE)) {
            entity.setStatus(LikeStatus.DISLIKE);
        } else if (entity.getStatus()!=null&&entity.getStatus().equals(LikeStatus.DISLIKE)) {
            entity.setStatus(null);
        } else {
            entity.setStatus(LikeStatus.DISLIKE);
        }
        repository.save(entity);
        entity.setUpdatedDate(LocalDateTime.now());

        return "DisLike successful!!!";

    }
}
