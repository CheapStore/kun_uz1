package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.*;
import com.example.Lesson_26_kun_uz1.Enums.ArticleStatus;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    private ArticleCustomRepository customRepository;

    @Autowired
    private ArticleRepository articleRepository;

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
            log.warn("id not found!!!");
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

        Optional<ArticleEntity> articleEntity1 = articleRepository.findById(id);
        if (articleEntity1.isEmpty()) {
            log.warn("ArticleID not fount!!!");
            throw new AppBadException("ArticleID not fount!!!");
        }
        ArticleEntity articleEntity = articleEntity1.get();
        ReturningData data = new ReturningData();
        data.setContent(articleEntity.getContent());
        data.setTitle(articleEntity.getTitle());
        data.setDescription(articleEntity.getDescription());
        data.setImagesId(articleEntity.getImageId());
        switch (lan) {
            case UZ -> {
                data.setRegionName(articleEntity.getRegion().getNameUz());
                data.setCategoryName(articleEntity.getCategory().getNameUz());
            }
            case RU -> {
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

    public List<ArticleDTO> ArticleByTypesAndExceptArticleId(Integer typesID, String articleID) {
        List<String> returningArticleID = repository.findBy1(typesID, 4);
        if (returningArticleID.isEmpty()) {
            log.warn("TypesID not fount!!!");
            throw new AppBadException("TypesID not fount!!!");
        }
        List<ArticleDTO> list = new ArrayList<>();
        for (String s : returningArticleID) {
            if (!s.equals(articleID)) {
                ArticleEntity articleEntity = articleRepository.findById(s).get();
                list.add(dto(articleEntity));
            }
        }
        return list;

    }

    public List<String> GetReaderArticle() {
        return articleRepository.getReaderArticle();
    }

    public List<ReturningData> articleIdAndRegionID(Integer articleTypesId, Integer regionID) {
        List<String> articleIdList = repository.findBy(articleTypesId, 5);
        List<ReturningData> list = new ArrayList<>();
        for (String id : articleIdList) {
            Optional<ArticleEntity> articleEntities = articleRepository.find(id, regionID);
            ArticleEntity articleEntity = articleEntities.get();
            ReturningData data = new ReturningData();
            data.setRegionName(articleEntity.getRegion().getNameEn());
            data.setContent(articleEntity.getContent());
            data.setTitle(articleEntity.getTitle());
            data.setImagesId(articleEntity.getImageId());
            data.setDescription(articleEntity.getDescription());
            data.setCategoryName(articleEntity.getCategory().getNameUz());
            list.add(data);
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

    public Page<ReturningData> regionIdAndLan(Integer regionID, Language lan, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<ArticleEntity> articleEntity = articleRepository.findRegionID(regionID, pageable);
        List<ArticleEntity> content = articleEntity.getContent();
        Long total = articleEntity.getTotalElements();
        List<ReturningData> list = new ArrayList<>();
        for (ArticleEntity entity : articleEntity) {
            ReturningData data = new ReturningData();
            data.setDescription(entity.getDescription());
            data.setContent(entity.getContent());
            data.setTitle(entity.getTitle());
            data.setImagesId(entity.getImageId());
            switch (lan) {
                case UZ -> {
                    data.setRegionName(entity.getRegion().getNameUz());
                    data.setCategoryName(entity.getCategory().getNameUz());
                }
                case RU -> {
                    data.setRegionName(entity.getRegion().getNameRu());
                    data.setCategoryName(entity.getCategory().getNameRu());
                }
                default -> {
                    data.setRegionName(entity.getRegion().getNameEn());
                    data.setCategoryName(entity.getCategory().getNameEn());
                }
            }
            list.add(data);
        }


        return new PageImpl<>(list, pageable, total);

    }

    public List<ReturningData> CategoryIDAndLan(Integer categoryID, Language lan) {
        List<ArticleEntity> categoryList = articleRepository.findCategoryId(categoryID);

        if (categoryList.isEmpty()) {
            log.warn("bunday Id li category yo`q");
            throw new AppBadException("bunday Id li category yo`q");
        }
        List<ReturningData> list = new ArrayList<>();
        for (ArticleEntity articleEntity : categoryList) {
            ReturningData data = new ReturningData();
            data.setDescription(articleEntity.getDescription());
            data.setContent(articleEntity.getContent());
            data.setTitle(articleEntity.getTitle());
            data.setImagesId(articleEntity.getImageId());
            switch (lan) {
                case UZ -> {
                    data.setRegionName(articleEntity.getRegion().getNameUz());
                    data.setCategoryName(articleEntity.getCategory().getNameUz());
                }
                case RU -> {
                    data.setRegionName(articleEntity.getRegion().getNameRu());
                    data.setCategoryName(articleEntity.getCategory().getNameRu());
                }
                default -> {
                    data.setRegionName(articleEntity.getRegion().getNameEn());
                    data.setCategoryName(articleEntity.getCategory().getNameEn());
                }
            }
            list.add(data);

        }
        return list;

    }

    public Object categoryIdAndLan(Integer categoryId, Language lan, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<ArticleEntity> articleEntity = articleRepository.findCategory(categoryId, pageable);
        List<ArticleEntity> content = articleEntity.getContent();
        long total = articleEntity.getTotalElements();
        List<ReturningData> list = new ArrayList<>();
        for (ArticleEntity entity : content) {
            ReturningData data = new ReturningData();
            data.setDescription(entity.getDescription());
            data.setContent(entity.getContent());
            data.setTitle(entity.getTitle());
            data.setImagesId(entity.getImageId());
            switch (lan) {
                case UZ -> {
                    data.setRegionName(entity.getRegion().getNameUz());
                    data.setCategoryName(entity.getCategory().getNameUz());
                }
                case RU -> {
                    data.setRegionName(entity.getRegion().getNameRu());
                    data.setCategoryName(entity.getCategory().getNameRu());
                }
                default -> {
                    data.setRegionName(entity.getRegion().getNameEn());
                    data.setCategoryName(entity.getCategory().getNameEn());
                }
            }
            list.add(data);
        }


        return new PageImpl<>(list, pageable, total);

    }

    public String viewCountPlus(String articleID) {
        Optional<ArticleEntity> articleEntity1 = articleRepository.findById(articleID);
        if (articleEntity1.isEmpty()) {
            log.warn("articleID wrong!!!");
            throw new AppBadException("articleID wrong!!!");
        }
        ArticleEntity articleEntity = articleEntity1.get();
        articleEntity.setViewCount(articleEntity.getViewCount() + 1);
        articleRepository.save(articleEntity);
        return "plus";
    }

    public Object ShareViewCount(String articleID) {
        Optional<ArticleEntity> articleEntity1 = articleRepository.findById(articleID);
        if (articleEntity1.isEmpty()) {
            log.warn("articleID wrong!!!" +
                    "SharedCount noPlus 🤦‍♂️");
            throw new AppBadException("articleID wrong!!!");
        }
        ArticleEntity articleEntity = articleEntity1.get();
        articleEntity.setSharedCount(articleEntity.getSharedCount() + 1);
        articleRepository.save(articleEntity);
        return "SharedCount plus";
    }

    public PageImpl<ArticleFilterDTO> filter(Integer page, Integer size, ArticleFilterDTO filter) {

        PaginationResultDTO<ArticleEntity> filter1 = customRepository.filter(page, size, filter);
        List<ArticleFilterDTO> list=new LinkedList<>();
        for (ArticleEntity articleEntity : filter1.getList()) {
            list.add(dto1(articleEntity));
        }
        Pageable paging = PageRequest.of(page - 1,size, Sort.Direction.DESC, "createdDate");

        return new PageImpl<>(list, paging,filter1.getTotalElement());


    }

    private ArticleFilterDTO dto1(ArticleEntity articleEntity) {
        ArticleFilterDTO dto=new ArticleFilterDTO();
        dto.setCategory_id(articleEntity.getCategoryId());
        dto.setRegion_id(articleEntity.getRegionId());
        dto.setStatus(articleEntity.getStatus());
        dto.setTitle(articleEntity.getTitle());
        return dto;
    }
}

