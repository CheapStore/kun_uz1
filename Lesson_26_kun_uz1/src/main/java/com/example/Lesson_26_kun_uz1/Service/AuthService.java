package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.AuthDTO;
import com.example.Lesson_26_kun_uz1.DTO.ProfileDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private Profilerepository profilerepository;

    public ProfileDTO auth(AuthDTO auth) {
        Optional<ProfileEntity> entity = profilerepository.findByEmailAndPassword(auth.getEmail(), MDUtil.encode(auth.getPassword()));

        if (entity.isEmpty()) {
            throw new AppBadException("Email or password is wrong");
        }
        ProfileEntity profileEntity = entity.get();
        ProfileDTO dto = new ProfileDTO();
        dto.setName(profileEntity.getName());
        dto.setJwt(JWTUtil.encode(profileEntity.getId(),profileEntity.getRole()));
        dto.setSurname(profileEntity.getSurname());
        dto.setRole(profileEntity.getRole());
        return dto;

    }


}
