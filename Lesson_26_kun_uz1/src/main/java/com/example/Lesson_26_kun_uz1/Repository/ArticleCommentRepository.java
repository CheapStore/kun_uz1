package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentEntity;
import com.example.Lesson_26_kun_uz1.Entity.TypesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleCommentRepository extends CrudRepository<ArticleCommentEntity,Integer>, PagingAndSortingRepository<ArticleCommentEntity,Integer> {



    @Query("from ArticleCommentEntity ace where ace.article.id=?1")
    List<ArticleCommentEntity> findArticleId(String articleID);



}
