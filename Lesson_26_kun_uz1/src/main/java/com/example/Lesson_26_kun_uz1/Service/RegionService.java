package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.RegionDTO;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.RegionRepository;
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
public class RegionService {
    @Autowired
    private RegionRepository repository;

    public RegionDTO create(RegionDTO dto) {
        RegionEntity regionEntity = new RegionEntity();
        regionEntity.setCreatedDate(LocalDateTime.now());
        regionEntity.setVisible(true);
        regionEntity.setNameRu(dto.getNameRu());
        regionEntity.setNameEn(dto.getNameEn());
        regionEntity.setNameUz(dto.getNameUz());
        regionEntity.setOrderNumber(dto.getOrderNumber());
        repository.save(regionEntity);
        return dto(regionEntity);
    }

    public Boolean update(Integer id, RegionDTO dto) {
        Optional<RegionEntity> byId = repository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("id wrong");
        }
        RegionEntity regionEntity = byId.get();
        regionEntity.setOrderNumber(dto.getOrderNumber());
        regionEntity.setVisible(dto.getVisible());
        regionEntity.setNameUz(dto.getNameUz());
        regionEntity.setNameEn(dto.getNameEn());
        regionEntity.setNameRu(dto.getNameRu());
        regionEntity.setUpdatedDate(LocalDateTime.now());
        repository.save(regionEntity);
        return true;
    }

    public Boolean delete(Integer id) {
        Optional<RegionEntity> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("id wrong");
        }
        Integer delete = repository.delete(optional.get().getId());
        return delete != 0;
    }

    public List<RegionDTO> getLanguage(String language) {

        Language language1=Language.valueOf(language);
        Iterable<RegionEntity> all = repository.findAll();
        List<RegionDTO> articleDTOS = new ArrayList<>();
        for (RegionEntity regionEntity : all) {
            RegionDTO regionDTO = new RegionDTO();
            regionDTO.setId(regionDTO.getId());
            switch (language1) {
                case UZ -> regionDTO.setName(regionEntity.getNameUz());
                case RU -> regionDTO.setName(regionEntity.getNameRu());
                case EN -> regionDTO.setName(regionEntity.getNameEn());
                default -> throw new AppBadException("Bunday tilda yo`q!!!");
            }
            articleDTOS.add(regionDTO);
        }

        return articleDTOS;
    }

    public Page<RegionDTO> pagination(Integer size, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<RegionEntity> studentPage = repository.findAll(pageable);
        List<RegionEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<RegionDTO> dtoList = new ArrayList<>();

        for (RegionEntity courseEntity : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    public RegionDTO dto(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setVisible(entity.getVisible());
        dto.setUpdatedDate(entity.getUpdatedDate());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


}