package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.TagNameDTO;
import com.example.Lesson_26_kun_uz1.Entity.TagNameEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.TagNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TagNameService {


    @Autowired
    private TagNameRepository tagNameRepository;


    public TagNameDTO create(TagNameDTO dto) {

        TagNameEntity entity=new TagNameEntity();

        entity.setTagName(dto.getName());
        entity.setCreatedDate(LocalDateTime.now());

        tagNameRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setName(entity.getTagName());

        return dto;
    }


    public TagNameEntity get(Long tagId){
        return tagNameRepository.findById(tagId).orElseThrow(() -> new AppBadException("Tag not found"));
    }
}
