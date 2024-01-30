package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.AuthDTO;
import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.DTO.ProfileDTO;
import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private Profilerepository profileRepository;

    @Autowired
    private MailSenderService mailSender;

    public ProfileDTO auth(AuthDTO auth) {
        Optional<ProfileEntity> entity = profileRepository.findByEmailAndPassword(auth.getEmail(), MDUtil.encode(auth.getPassword()));

        if (entity.isEmpty()) {
            throw new AppBadException("Email or password is wrong");
        }

        ProfileEntity profileEntity1 = entity.get();


        if (!profileEntity1.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppBadException("Profile not active");
        }

        ProfileEntity profileEntity = entity.get();
        ProfileDTO dto = new ProfileDTO();
        dto.setName(profileEntity.getName());
        dto.setJwt(JWTUtil.encode(profileEntity.getId(), profileEntity.getRole()));
        dto.setSurname(profileEntity.getSurname());
        dto.setRole(profileEntity.getRole());

        return dto;

    }

    public Boolean registration(RegistirationProfileDTO dto) { // login
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                // delete
                // or
                //send verification code (email/sms)
            } else {
                throw new AppBadException("Email exists");
            }
        }
        // create
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.USER);
        entity.setPhone(dto.getPhone());
        entity.setSms("1234");
        profileRepository.save(entity);
        String jwt = JWTUtil.encodeForEmail(entity.getId());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8080/auth/verification/email/" + jwt;

        //send verification code (email/sms)
        mailSender.sendEmail(dto.getEmail(), "Complete REGISTRATION", text);


        return true;
    }

    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);

            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
    }

}
