package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.CustomRepository;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private CustomRepository customRepository;


    @Autowired
    private Profilerepository profilerepository;

    public CreateProfileDTO create(CreateProfileDTO dto) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setPassword(MDUtil.encode(dto.getPassword()));
        profileEntity.setPhone(dto.getPhone());
        profileEntity.setName(dto.getName());
        profileEntity.setSurname(dto.getSurname());
        profileEntity.setStatus(dto.getStatus());
        profileEntity.setRole(dto.getRole());
        profileEntity.setEmail(dto.getEmail());
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileEntity.setSms("1");
        profileEntity.setTime(LocalDateTime.now());
        profilerepository.save(profileEntity);
        return dto;

    }

    public boolean update(Integer id, UpdateProfileDTO dto) {
        Optional<ProfileEntity> byId = profilerepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("id wrong!!!");
        }
        ProfileEntity profileEntity = byId.get();
        profileEntity.setRole(dto.getRole());
        profileEntity.setName(dto.getName());
        profileEntity.setEmail(dto.getEmail());
        profileEntity.setStatus(dto.getStatus());
        profileEntity.setUpdatedDate(LocalDateTime.now());
        profilerepository.save(profileEntity);
        return true;

    }

    public PageImpl<ProfileDTO> getList(Integer size, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<ProfileEntity> studentPage = profilerepository.findAll(pageable);
        List<ProfileEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<ProfileDTO> dtoList = new ArrayList<>();

        for (ProfileEntity courseEntity : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    public ProfileDTO dto(ProfileEntity entity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setRole(entity.getRole());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setCreatedDate(entity.getCreatedDate());
        profileDTO.setPassword(entity.getPassword());
        profileDTO.setId(entity.getId());
        return profileDTO;
    }

    public PageImpl<ProfileDTO> filter(Integer page,Integer size, FilterDTO filter) {

        PaginationResultDTO<ProfileEntity> filter1 = customRepository.filter(page,size, filter);
        List<ProfileDTO> list = new LinkedList<>();
        for (ProfileEntity courseEntity : filter1.getList()) {
            list.add(dto(courseEntity));
        }

        Pageable paging = PageRequest.of(page - 1,size, Sort.Direction.DESC, "createdDate");

        return new PageImpl<ProfileDTO>(list, paging,filter1.getTotalElement());



    }

    public ProfileDTO updateDetail(Integer id,ProfileDTO profileDTO) {
        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setPassword(profileDTO.getPassword());
        profilerepository.save(profileEntity);
        return profileDTO;

    }
    public ProfileEntity get(Integer id){
       return profilerepository.findById(id).orElseThrow(() -> new AppBadException("Profile not fount"));
    }
}



