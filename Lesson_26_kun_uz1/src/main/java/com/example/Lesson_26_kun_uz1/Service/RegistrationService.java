package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.SMSHistory;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Repository.RegistrationRepository;
import com.example.Lesson_26_kun_uz1.Repository.SMSHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private Profilerepository profilerepository;
    @Autowired
    private SMSHistoryRepository smsHistoryRepository;
    private static String email1 = "allayarovshahzodbekz@gmail.com";
    private static String password = "kqvmpnebrzwmqowa";

    public String registerEmail(RegistirationProfileDTO registirationProfileDTO) {


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


        try {
            sendMail(profileEntity.getEmail(), profileEntity.getSms());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Sms jo`natildi:";


    }

    public String register(RegistirationProfileDTO registirationProfileDTO) {
        SMSHistory smsHistory = new SMSHistory();
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setPassword(registirationProfileDTO.getPassword());
        profileEntity.setName(registirationProfileDTO.getName());
        profileEntity.setPhone(registirationProfileDTO.getPhone());
        profileEntity.setSurname(registirationProfileDTO.getSurName());
        profileEntity.setRole(ProfileRole.USER);
        profileEntity.setStatus(ProfileStatus.REGISTRATION);
        profileEntity.setCreatedDate(LocalDateTime.now());
        profileEntity.setEmail(registirationProfileDTO.getEmail());
        profileEntity.setTime(LocalDateTime.now());
        profileEntity.setSms(password());
        repository.save(profileEntity);
        smsHistory.setReason(ProfileStatus.REGISTRATION.name());
        smsHistory.setCod(profileEntity.getSms());
        smsHistory.setProfileId(profileEntity.getId());
        smsHistory.setCreatedDate(LocalDateTime.now());
        smsHistory.setPhone(registirationProfileDTO.getPhone());
        smsHistoryRepository.save(smsHistory);
        return "Sms jo`natildi:";
    }

    public void sendMail(String email, String cod) throws MessagingException {
        Session session = getSession(getProperties(), email1, password);
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(email1);
        mimeMessage.setRecipients(Message.RecipientType.TO, email);
        mimeMessage.setContent(cod, "text/plain");
        mimeMessage.setSubject("codeuz");
        Transport.send(mimeMessage);
    }

    public Session getSession(Properties properties, String email, String password) {
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return properties;
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


    public String updateStatus(String kod) {
        ProfileEntity profileEntity = profilerepository.findBySms(kod).get();

        if (profileEntity.getTime()!=null){
            if (LocalDateTime.now().getMinute() - profileEntity.getTime().getMinute() > 1) {
                String password1 = password();
                profilerepository.updateSMS(kod,password1);
                SMSHistory smsHistory=new SMSHistory();
                smsHistory.setPhone(profileEntity.getPhone());
                smsHistory.setCod(password1);
                smsHistory.setReason("Takror ");
                smsHistory.setCreatedDate(LocalDateTime.now());
                smsHistoryRepository.save(smsHistory);
                throw new AppBadException("Sms vaqti tugadi!!! \n" +
                        "Qayta jo`natildi:");
            }
                profilerepository.sms(kod);
                return "Ro`yxatdan o`tdingiz";

        }

        return null;



    }
}

