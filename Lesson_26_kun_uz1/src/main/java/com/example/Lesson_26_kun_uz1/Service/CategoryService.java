package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.CategoryCreateDTO;
import com.example.Lesson_26_kun_uz1.DTO.CategoryDTO;
import com.example.Lesson_26_kun_uz1.DTO.RegionDTO;
import com.example.Lesson_26_kun_uz1.Entity.CategoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryCreateDTO create(CategoryCreateDTO dto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNameEn(dto.getNameEn());
        categoryEntity.setNameUz(dto.getNameUz());
        categoryEntity.setNameRu(dto.getNameRu());
        categoryEntity.setOrderNumber(dto.getOrderNumber());
        categoryEntity.setCreatedDate(LocalDateTime.now());
        categoryRepository.save(categoryEntity);
        return dto;
    }

    public boolean update(Integer id, CategoryCreateDTO categoryCreateDTO) {
        Optional<CategoryEntity> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("id category wrong!.");
        }
        CategoryEntity categoryEntity = byId.get();
        categoryEntity.setUpdatedDate(LocalDateTime.now());
        categoryEntity.setNameEn(categoryCreateDTO.getNameEn());
        categoryEntity.setNameUz(categoryCreateDTO.getNameUz());
        categoryEntity.setNameRu(categoryCreateDTO.getNameRu());
        categoryEntity.setOrderNumber(categoryCreateDTO.getOrderNumber());
        categoryRepository.save(categoryEntity);
        return true;
    }

    public boolean delete(Integer id) {
        return categoryRepository.delete(id) != 0;
    }


    public Page<CategoryDTO> pagination(Integer size, Integer page) {
        Sort sort= Sort.by(Sort.Direction.DESC,"orderNumber");
        PageRequest pageable = PageRequest.of(page - 1, size,sort);
        Page<CategoryEntity> studentPage = categoryRepository.findAll(pageable);
        List<CategoryEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<CategoryDTO> dtoList = new ArrayList<>();

        for (CategoryEntity courseEntity : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    public CategoryDTO dto(CategoryEntity categoryEntity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(categoryEntity.getId());
        dto.setCreatedDate(categoryEntity.getCreatedDate());
        dto.setVisible(categoryEntity.getVisible());
        dto.setUpdatedDate(categoryEntity.getUpdatedDate());
        dto.setOrderNumber(categoryEntity.getOrderNumber());
        dto.setNameEn(categoryEntity.getNameEn());
        dto.setNameRu(categoryEntity.getNameRu());
        dto.setNameUz(categoryEntity.getNameUz());
        return dto;
    }

    public Object getLanguage(String language) {
        Language language1=Language.valueOf(language);
        Iterable<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO> articleDTOS = new ArrayList<>();
        for (CategoryEntity entity : all) {
            CategoryDTO categorydto = new CategoryDTO();
            categorydto.setId(entity.getId());
            categorydto.setOrderNumber(entity.getOrderNumber());
            switch (language1) {
                case UZ -> categorydto.setName(entity.getNameUz());
                case RU -> categorydto.setName(entity.getNameRu());
                default ->  categorydto.setName(entity.getNameEn());
            }
            articleDTOS.add(categorydto);
        }

        return articleDTOS;
    }
}

