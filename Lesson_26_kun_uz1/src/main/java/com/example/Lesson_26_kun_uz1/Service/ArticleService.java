package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.CategoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.ArticleRepository;
import com.example.Lesson_26_kun_uz1.Repository.ArticleTypesRepository;
import com.example.Lesson_26_kun_uz1.Repository.CategoryRepository;
import com.example.Lesson_26_kun_uz1.Repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.example.Lesson_26_kun_uz1.Enums.Language.RU;
import static com.example.Lesson_26_kun_uz1.Enums.Language.UZ;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RegionRepository regionRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleTypesService articleTypesService;

    @Autowired
    private ArticleTypesRepository repository;

    public Object create(CreateArticleDTO dto, Integer profileId) {
        ArticleEntity article = new ArticleEntity();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setImageId(dto.getImagesId());
        article.setVisible(false);
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
        if (update.isEmpty()) {
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


    public Boolean updateVisible(String id) {
        Integer i = articleRepository.updateVisible(id);
        return i != 0;
    }

    public Boolean updateStatus(String id, Integer publisherID) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            log.warn("id not found!!!");
            throw new AppBadException("id not found!!!");
        }
        ArticleEntity articleEntity = optional.get();
        articleEntity.setPublisherId(publisherID);
        articleEntity.setVisible(true);
        articleEntity.setStatus(ArticleStatus.PUPLISHED);
        articleRepository.save(articleEntity);
        return null;
    }

    public List<ArticleDTO> ArticleByTypes(Integer id, Integer size) {
        List<String> articleID = repository.findBy(id, size);
        List<ArticleDTO> list = new LinkedList<>();
        for (String ArticleID : articleID) {
            ArticleEntity articleEntity = articleRepository.findById(ArticleID).get();
            list.add(dto(articleEntity));
        }
        return list;
    }

    public ArticleDTO dto(ArticleEntity entity) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setCreatedDate(entity.getCreatedDate());
        articleDTO.setId(entity.getId());
        articleDTO.setImagesId(entity.getImageId());
        articleDTO.setContent(entity.getContent());
        return articleDTO;
    }

    public List<ArticleDTO> ArticleSwitchId(Integer id) {
        List<String> articleID = repository.findByid(id, 8);
        List<ArticleDTO> list = new LinkedList<>();
        for (String ArticleID : articleID) {
            ArticleEntity articleEntity = articleRepository.findById(ArticleID).get();
            list.add(dto(articleEntity));
        }
        return list;
    }

    public ReturningData getLanguage(String id, Language lan) {
        List<ArticleDTO>list=new LinkedList<>();
        ArticleEntity articleEntity = articleRepository.findById(id).get();
        ReturningData data=new ReturningData();
        data.setContent(articleEntity.getContent());
        data.setTitle(articleEntity.getTitle());
        data.setDescription(articleEntity.getDescription());
        data.setImagesId(articleEntity.getImageId());
        switch (lan){
            case UZ ->{
                data.setRegionName(articleEntity.getRegion().getNameUz());
                data.setCategoryName(articleEntity.getCategory().getNameUz());
            }
            case RU ->{
                data.setRegionName(articleEntity.getRegion().getNameRu());
                data.setCategoryName(articleEntity.getCategory().getNameRu());
            }
            default -> {
                data.setRegionName(articleEntity.getRegion().getNameEn());
                data.setCategoryName(articleEntity.getCategory().getNameEn());
            }

        }


        return data;
    }

}

