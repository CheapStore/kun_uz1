package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private Profilerepository profilerepository;

    public String register(RegistirationProfileDTO registirationProfileDTO) {
        profilerepository.findByEmail(registirationProfileDTO.getEmail());
//        Optional<ProfileEntity> byEmailOrPhone = profilerepository.findByEmailOrPhone(registirationProfileDTO.getEmail(), registirationProfileDTO.getPhone());

//        if (byEmailOrPhone.isEmpty()) {
            ProfileEntity profileEntity = new ProfileEntity();
            profileEntity.setPassword(registirationProfileDTO.getPassword());
            profileEntity.setName(registirationProfileDTO.getName());
            profileEntity.setPhone(registirationProfileDTO.getPhone());
            profileEntity.setSurname(registirationProfileDTO.getSurName());
            profileEntity.setRole(ProfileRole.USER);
            profileEntity.setStatus(ProfileStatus.BLOCK);
            profileEntity.setCreatedDate(LocalDateTime.now());
            profileEntity.setEmail(registirationProfileDTO.getEmail());
            profileEntity.setSms(password());
            repository.save(profileEntity);
            return "Sms jo`natildi:";

//        }else {
//            throw new AppBadException("bunday profile bor!!!");
//        }




    }

    public String password(){
        Random random=new Random();
        String parol="0123456789";
        StringBuilder password=new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(parol.length());
            password.append(parol.charAt(index));
        }
        return password.toString();


    }


    public String updateStatus(String kod) {
        Integer sms = profilerepository.sms(kod, ProfileStatus.ACTIVE);
        if (sms==1){
            return "Ro`yxatdan o`tdingiz";
        }
        throw new AppBadException("sms xato!!!");
    }
}

