package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {


//    @Query("from ArticleEntity where id=?1")
//    Optional<ArticleEntity> findArticleById(String id);


    //    @Query(value = "from ArticleEntity where id=?1")
    Optional<ArticleEntity> findById(String id);

    @Modifying
    @Transactional
    @Query("update ArticleEntity " +
            "SET visible = false " +
            "WHERE id=?1")
    Integer updateVisible(String id);


    @Query(value = "from ArticleEntity a where a.category.nameEn=?1 or a.category.nameRu=?1 or a.category.nameUz=?1 " +
            " and a.region.nameRu=?1 or a.region.nameUz=?1 or a.region.nameEn=?1 order by a.createdDate desc ")
    List<ArticleEntity> lang(String lan);


    @Query(value = "select a.id from article a order by view_count  desc limit 3 ", nativeQuery = true)
    List<String> getReaderArticle();

    @Query("from ArticleEntity a where a.id=?1 and a.region.id=?2 order by a.createdDate desc")
    Optional<ArticleEntity> find(String id, Integer regionID);

    @Query("from ArticleEntity a where a.region.id=?1 order by a.createdDate ")
    Page<ArticleEntity> findRegionID(Integer regionID, PageRequest pageable);
    @Query("from ArticleEntity a where a.category.id=?1 order by a.createdDate limit 5")

    List<ArticleEntity> findCategoryId(Integer categoryID);
    @Query("from ArticleEntity a where a.category.id=?1 order by a.createdDate ")
    Page<ArticleEntity> findCategory(Integer categoryId, PageRequest pageable);

}
