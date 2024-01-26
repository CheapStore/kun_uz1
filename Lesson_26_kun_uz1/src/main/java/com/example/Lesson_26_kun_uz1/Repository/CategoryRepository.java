package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.DTO.CategoryCreateDTO;
import com.example.Lesson_26_kun_uz1.Entity.CategoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {

    @Transactional
    @Modifying
    @Query("update CategoryEntity set visible=false where id=:id")
    Integer delete (@RequestParam("id")Integer id);

    Page<CategoryEntity> findAll(Pageable pageable);


}
