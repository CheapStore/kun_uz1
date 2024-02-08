package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.TypesDTO;
import com.example.Lesson_26_kun_uz1.Entity.TypesEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypesService {
    @Autowired
    private TypesRepository typesRepository;


    public TypesDTO create(TypesDTO DTO) {
        TypesEntity article = new TypesEntity();
        article.setCreatedDate(LocalDateTime.now());
        article.setNameEn(DTO.getNameEn());
        article.setNameUz(DTO.getNameUz());
        article.setNameRu(DTO.getNameRu());
        article.setVisible(true);
        article.setUpdatedDate(DTO.getUpdatedDate());
        typesRepository.save(article);
        return DTO;


    }

    public boolean update(Integer id, TypesDTO articleDTO) {
        Optional<TypesEntity> byId = typesRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("article wtong!!!");
        }
        TypesEntity entity = byId.get();
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setNameRu(articleDTO.getNameRu());
        entity.setNameUz(articleDTO.getNameUz());
        entity.setNameEn(articleDTO.getNameEn());
        entity.setVisible(articleDTO.getVisible());
        typesRepository.save(entity);
        return true;


    }

    public Boolean delete(Integer id) {

        Integer i = typesRepository.deleteUpdate(id);
        if (i == 0) {
            return false;
        }
        return true;
    }

    public List<TypesDTO> getlistLangue(Language language) {
        Iterable<TypesEntity> all = typesRepository.findAll();
        List<TypesDTO> articleDTOS = new ArrayList<>();
        for (TypesEntity articleEntity : all) {
            TypesDTO articleDTO = new TypesDTO();
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

    public Page<TypesDTO> pagination(Integer size, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<TypesEntity> studentPage = typesRepository.findAll(pageable);
        List<TypesEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<TypesDTO> dtoList = new ArrayList<>();

        for (TypesEntity courseEntity  : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }


    public TypesDTO dto(TypesEntity entity) {
        TypesDTO dto = new TypesDTO();
        dto.setId(entity.getId());

        dto.setVisible(entity.getVisible());
        dto.setUpdatedDate(entity.getUpdatedDate());

        return dto;
    }

    public TypesDTO createModerator(TypesDTO articleDTO) {
//        1. CREATE (Moderator) status(NotPublished)
//        (title,description,content,image_id, region_id,category_id, articleType(array))

        return null;
    }
}
