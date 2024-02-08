package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.TypesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface TypesRepository extends CrudRepository<TypesEntity, Integer>, PagingAndSortingRepository<TypesEntity,Integer> {


    @Transactional
    @Modifying
    @Query("update TypesEntity  a set a.visible=false  where a.id=:id ")
    Integer deleteUpdate(@RequestParam("id")Integer id);
}
