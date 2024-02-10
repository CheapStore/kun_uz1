package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleLikeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends CrudRepository<ArticleLikeEntity,Integer> {
    @Query("from ArticleLikeEntity a where  a.article.id=?1 and  a.profile.id=?2")
    Optional<ArticleLikeEntity> find(String articleID, Integer profileID);
}
