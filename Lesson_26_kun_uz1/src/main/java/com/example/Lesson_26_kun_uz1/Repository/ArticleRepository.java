package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {



//    @Query("from ArticleEntity where id=?1")
//    Optional<ArticleEntity> findArticleById(String id);


//    @Query(value = "from ArticleEntity where id=?1")
    Optional<ArticleEntity> findById(String id);
}
