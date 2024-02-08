package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.CreateArticleDTO;
import com.example.Lesson_26_kun_uz1.DTO.UpdateArticleDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleTypesService articleTypesService;

    public Object create(CreateArticleDTO dto, Integer profileId) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setImageId(dto.getImagesId());
        article.setRegionId(dto.getRegionId());
        article.setCategoryId(dto.getCagtegoryId());
        article.setModeratorId(profileId);
        articleRepository.save(article);

        articleTypesService.create(article.getId(), dto.getTypes());
        return dto;

    }

    //    public String update(String id, UpdateArticleDTO dto) {
//        ArticleEntity check = check(id);
//        check.setContent(dto.getContent());
//        check.setCategoryId(dto.getCategoryId());
//        check.setTitle(dto.getTitle());
//        check.setDescription(dto.getDescription());
//        check.setId(dto.getImagesId());
//        check.setRegionId(dto.getRegionId());
//        articleRepository.save(check);
//        return "update successful";
//
//    }
//
//    public ArticleEntity check(String id){
//        return articleRepository.findArticleById(id).orElseThrow(() -> new AppBadException("Article not found"));
//
//    }
    public String update(String id, UpdateArticleDTO dto) {
        Optional<ArticleEntity> update = articleRepository.findById(id);
        if (update.isEmpty()){
            throw new AppBadException("id not found!!!");
        }
        ArticleEntity articleEntity = update.get();
        articleEntity.setContent(dto.getContent());
        articleEntity.setCategoryId(dto.getCategoryId());
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setDescription(dto.getDescription());
        articleEntity.setId(dto.getImagesId());
        articleEntity.setRegionId(dto.getRegionId());
        articleRepository.save(articleEntity);
        return "update successful";

    }


}
