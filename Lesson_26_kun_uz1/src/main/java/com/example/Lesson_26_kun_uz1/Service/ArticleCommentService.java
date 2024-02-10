package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.CreateCommentDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentEntity;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleCommentRepository;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ArticleCommentService {
    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private ArticleRepository repository;


    public String  create(CreateCommentDTO dto) {
        Optional<ArticleEntity> article = repository.findById(dto.getArticleId());
        if (article.isEmpty()) {
            log.warn("bunday articleID yo`q comment yaratilmadi!!!");
            throw new AppBadException("bunday articleID yo`q");
        }
        ArticleCommentEntity entity=new ArticleCommentEntity();
        entity.setArticleId(dto.getArticleId());
        entity.setContent(dto.getContent());
        articleCommentRepository.save(entity);
        return "create successful.";

    }
}
