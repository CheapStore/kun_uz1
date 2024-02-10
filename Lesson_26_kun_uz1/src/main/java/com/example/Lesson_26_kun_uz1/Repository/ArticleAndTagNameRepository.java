package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ArticleTagNameEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleAndTagNameRepository extends CrudRepository<ArticleTagNameEntity,Integer> {
}
