package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleTypesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleTypesRepository extends CrudRepository<ArticleTypesEntity, Integer> {

    //    @Query(value = "select   from article_types a where a.types.id=?1 order by a.createdDate",nativeQuery = true)
//    List<String> findBy(String id);
    @Query(value = "select ant.article_id from article_types ant where ant.types_id=?1  order by ant.created_date desc limit ?2 ",nativeQuery = true)
    List<String> findBy(Integer id,Integer size);
    @Query(value = "select ant.article_id from article_types ant where ant.types_id!=?1  order by ant.created_date desc limit ?2 ",nativeQuery = true)
    List<String> findByid(Integer id,Integer size);



    @Query(value = "select ant.article_id from article_types ant where ant.types_id=?1 order by ant.created_date desc limit ?2 ",nativeQuery = true)
    List<String> findBy1(Integer id,Integer size);
}
