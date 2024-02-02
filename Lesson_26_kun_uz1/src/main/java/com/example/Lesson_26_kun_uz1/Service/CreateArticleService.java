package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.CreateArticleDTO;
import com.example.Lesson_26_kun_uz1.Entity.CategoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.CreateArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Repository.CreateArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateArticleService {
    @Autowired
    private CreateArticleRepository repository;

    public void create(CreateArticleDTO dto, Integer moderatorId) {
        RegionEntity region = new RegionEntity();
        region.setId(dto.getRegionId());
        CategoryEntity category = new CategoryEntity();
        category.setId(dto.getCagtegoryId());
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setId(moderatorId);


        CreateArticleEntity article = new CreateArticleEntity();

        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setImagesId(dto.getImagesId());
        article.setRegion(region);
        article.setCategory(category);
        article.setProfile(profileEntity);
        repository.save(article);


    }
}
