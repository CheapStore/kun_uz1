package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import com.example.Lesson_26_kun_uz1.Util.MDUtil;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

//@Service
//public class InitService {
//    @Autowired
//    private Profilerepository profilerepository;
//    public void initAdmin() {
//        String adminEmail = "admin@mail.ru";
//        Optional<ProfileEntity> optional = profilerepository.findByEmail(adminEmail);
//        if (optional.isPresent()) {
//            return;
//        }
//         create admin
//        ProfileEntity admin = new ProfileEntity();
//        admin.setName("Admin");
//        admin.setSurname("Adminjon");
//        admin.setEmail(adminEmail);
//        admin.setStatus(ProfileStatus.ACTIVE);
//        admin.setRole(ProfileRole.ADMIN);
//        admin.setPassword(MDUtil.encode("12345"));
//        admin.setPhone("520");
//        profilerepository.save(admin);
//    }
//
//
//}
@Service
public class InitService {
    @Autowired
    private Profilerepository profilerepository;
    public void initAdmin() {
        String adminEmail = "admin@mail.ru";
        Optional<ProfileEntity> optional = profilerepository.findByEmail(adminEmail);
        if (optional.isPresent()) {
            return;
        }
        // create admin
        ProfileEntity admin = new ProfileEntity();
        admin.setName("Admin");
        admin.setSurname("Adminjon");
        admin.setEmail(adminEmail);
        admin.setSms("1111");
        admin.setTime(LocalDateTime.now());
        admin.setStatus(ProfileStatus.ACTIVE);
        admin.setRole(ProfileRole.ADMIN);
        admin.setPhone("+998997971636");
        admin.setPassword(MDUtil.encode("12345"));
        profilerepository.save(admin);
    }


}
