package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {


//    List<RegionEntity> findByLanguage(Language language);
    Page<RegionEntity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update ArticleEntity set visible=false where id=:id")
    Integer delete(@RequestParam("id")Integer id);
}
