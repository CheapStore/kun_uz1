package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.ArticleDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
        article.setCreatedDate(LocalDateTime.now());
        article.setNameEn(DTO.getNameEn());
        article.setNameUz(DTO.getNameUz());
        article.setNameRu(DTO.getNameRu());
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
        entity.setNameRu(articleDTO.getNameRu());
        entity.setNameUz(articleDTO.getNameUz());
        entity.setNameEn(articleDTO.getNameEn());
        entity.setVisible(articleDTO.getVisible());
        articleRepository.save(entity);
        return true;


    }

    public Boolean delete(Integer id) {

        Integer i = articleRepository.deleteUpdate(id);
        if (i == 0) {
            return false;
        }
        return true;
    }

    public List<ArticleDTO> getlistLangue(Language language) {
        Iterable<ArticleEntity> all = articleRepository.findAll();
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (ArticleEntity articleEntity : all) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(articleEntity.getId());
            switch (language) {
                case UZ -> articleDTO.setName(articleEntity.getNameUz());
                case RU -> articleDTO.setName(articleEntity.getNameRu());
                default -> articleDTO.setName(articleEntity.getNameEn());
            }
            articleDTOS.add(articleDTO);
        }

        return articleDTOS;

    }

    public Page<ArticleDTO> pagination(Integer size, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<ArticleEntity> studentPage = articleRepository.findAll(pageable);
        List<ArticleEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<ArticleDTO> dtoList = new ArrayList<>();

        for (ArticleEntity courseEntity : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }


    public ArticleDTO dto(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setArticleName(entity.getArticleName());
        dto.setVisible(entity.getVisible());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }
}
