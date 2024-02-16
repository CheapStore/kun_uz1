package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.EmailSendHistoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.EmailHistoryRepository;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @Autowired
    private ResourceBundleService resourceBundleService;


    @Autowired
    private Profilerepository profileRepository;
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    @Autowired
    private MailSenderService mailSender;

    public ProfileDTO auth(AuthDTO auth, Language language) {
        Optional<ProfileEntity> entity = profileRepository.findByEmailAndPassword(auth.getEmail(), MDUtil.encode(auth.getPassword()));
//        MDUtil.encode(auth.getPassword())
        if (entity.isEmpty()) {
            String message = resourceBundleService.getMessage("email.password.wrong", language);
            throw new AppBadException(message);
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

        dto.setJwt(JWTUtil.encode(profileEntity.getEmail(), profileEntity.getRole()));
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
        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime to = LocalDateTime.now();

        if (emailHistoryRepository.countSendEmail(dto.getEmail(), from, to) >= 3) {
            throw new AppBadException("To many attempt. Please try after 1 minute.");
        }

        EmailSendHistoryEntity emailSendHistory = new EmailSendHistoryEntity();
        // create
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setPhone(dto.getPhone());
        entity.setSms(password());
        entity.setTime(LocalDateTime.MAX);
        entity.setTime(LocalDateTime.now());
        profileRepository.save(entity);
        emailSendHistory.setCod(entity.getSms());
        emailSendHistory.setEmail(entity.getEmail());
        emailSendHistory.setCreatedDate(LocalDateTime.now());
        emailSendHistory.setSabab(ProfileStatus.REGISTRATION.name());
        emailHistoryRepository.save(emailSendHistory);
        String jwt = JWTUtil.encodeForEmail(entity.getId());
        String text = "<h1 style=\"text-align: center\">Hello %s</h1>\n" +
                "<p style=\"background-color: indianred; color: white; padding: 30px\">To complete registration please link to the following link</p>\n" +
                "<a style=\" background-color: #f44336;\n" +
                "  color: white;\n" +
                "  padding: 14px 25px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\" href=\"http://localhost:8080/email/verification/email/%s\n" +
                "\">Click</a>\n" +
                "<br>\n";
        text = String.format(text, entity.getName(), jwt);
//        mailSender.sendEmail(dto.getEmail(), "Complete registration", text);


        //send verification code (email/sms)
        mailSender.sendEmail(dto.getEmail(), "Complete REGISTRATION", text);


        return true;
    }

    public String password() {
        Random random = new Random();
        String parol = "0123456789";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(parol.length());
            password.append(parol.charAt(index));
        }
        return password.toString();


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

    public List<ProfileEntity> getAll() {
        Iterable<ProfileEntity> all = profileRepository.findAll();
        List<ProfileEntity> profileEntities = new ArrayList<>();
        for (ProfileEntity profileEntity : all) {
            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(profileEntity.getId());
            profileDTO.setRole(profileEntity.getRole());
            profileDTO.setName(profileEntity.getName());
            profileDTO.setSurname(profileEntity.getSurname());
            profileDTO.setPassword(profileDTO.getPassword());
            profileEntities.add(profileEntity);
        }
        return profileEntities;
    }


}
