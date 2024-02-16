package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Entity.ArticleTagNameEntity;
import com.example.Lesson_26_kun_uz1.Repository.ArticleAndTagNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAndTagNameService {
    @Autowired
    private ArticleAndTagNameRepository articleAndTagNameRepository;

    public void create(String articleId, List<Long> tagIdList) {
        for (Long typeId : tagIdList) {
            create(articleId, typeId);
        }
    }

    public void create(String articleId, Long tagId) {
        ArticleTagNameEntity entity = new ArticleTagNameEntity();
        entity.setArticleId(articleId);
        entity.setTagsId(tagId);
        articleAndTagNameRepository.save(entity);
    }
}
