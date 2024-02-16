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
            entity.setCreateDate(LocalDateTime.now());
            entity.setStatus(LikeStatus.LIKE);
            repository.save(entity);
            return "Like successful!!!";
        }

        ArticleCommentLikeEntity entity = optional.get();
        entity.setStatus(LikeStatus.LIKE);
        entity.setUpdateDate(LocalDateTime.now());
        repository.save(entity);

        return "Like successful!!!";

    }

    public String  disLike(Integer commentID, Integer profileId) {
        Optional<ArticleCommentLikeEntity> optional = repository.findByCommentIDAndProfileID(commentID.longValue(), profileId);
        if (optional.isEmpty()) {
            ArticleCommentLikeEntity entity = new ArticleCommentLikeEntity();
            entity.setCommentID(commentID.longValue());
            entity.setProfileID(profileId);
            entity.setCreateDate(LocalDateTime.now());
            entity.setStatus(LikeStatus.DISLIKE);
            repository.save(entity);
            return "DisLike successful!!!";
        }
        ArticleCommentLikeEntity entity = optional.get();
        entity.setStatus(LikeStatus.DISLIKE);
        entity.setUpdateDate(LocalDateTime.now());
        repository.save(entity);
        return "DisLike successful!!!";

    }

    public Boolean remove(Integer commentID, Integer profileID) {
        Optional<ArticleCommentLikeEntity> byCommentIDAndProfileID = repository.findByCommentIDAndProfileID(commentID.longValue(), profileID);
        if (byCommentIDAndProfileID.isEmpty()) {
            log.warn("like wrong");
            throw new AppBadException("like wrong");
        }

        ArticleCommentLikeEntity entity = byCommentIDAndProfileID.get();
        entity.setStatus(null);
        entity.setUpdateDate(LocalDateTime.now());
        repository.save(entity);
        return true;
    }
}
