package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.ArticleDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public ArticleDTO create(ArticleDTO DTO) {
        ArticleEntity article = new ArticleEntity();
        article.setArticleName(DTO.getArticleName());
        article.setLanguage(DTO.getLanguage());
        article.setCreatedDate(LocalDateTime.now());
        article.setVisible(true);
        article.setUpdatedDate(DTO.getUpdatedDate());
        articleRepository.save(article);
        return DTO;


    }

    public boolean update(Integer id, ArticleDTO articleDTO) {
        Optional<ArticleEntity> byId = articleRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("article wtong!!!");
        }
        ArticleEntity entity = byId.get();
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setArticleName(articleDTO.getArticleName());
        entity.setLanguage(articleDTO.getLanguage());
        entity.setVisible(articleDTO.getVisible());
        articleRepository.save(entity);
        return true;


    }

    public Boolean delete(Integer id) {
        articleRepository.deleteById(id);
        return true;
    }

    public List<Object> getlist(String language) {
        if (language.equals(Language.nameEN.toString()) || language.equals(Language.nameRU.toString()) || language.equals(Language.nameUZ.toString())) {
            Language language1 = Language.valueOf(language);
            List<ArticleEntity> byLanguage = articleRepository.findByLanguage(language1);
            List<Object> list1 = new LinkedList<>();
            for (ArticleEntity o : byLanguage) {
                List<Object> list = new LinkedList<>();
                list.add("id:" + o.getId());
                list.add("ArticleName:" + o.getArticleName());
                list.add("Visible:" + o.getVisible());
                list1.add(list);
            }
            return list1;
        } else {
            throw new AppBadException("language wrong!!!");
        }


    }
}
