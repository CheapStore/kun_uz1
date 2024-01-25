package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, Integer> {


//    @Query(value = "select id,article_name from article  where  language=?1",nativeQuery = true)
//    List<ArticleEntity> findByLanguage(String language);
    Page<ArticleEntity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ArticleEntity  a set a.visible=false  where a.id=:id ")
    Integer deleteUpdate(@RequestParam("id")Integer id);



}
