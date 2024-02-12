package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentLikeEntity;
import jdk.dynalink.Operation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentLikeRepository extends CrudRepository<ArticleCommentLikeEntity,Integer> {




    Optional<ArticleCommentLikeEntity>findByCommentIDAndProfileID(Long commentID,Integer profileID);
}
