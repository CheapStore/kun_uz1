package com.example.Lesson_26_kun_uz1.Config;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@Service
//public class CustomUserDetailService implements UserDetailsService {
//
//
//
//    @Autowired
//    public Profilerepository profilerepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // login/phone/email
//        Optional<ProfileEntity> optional = profilerepository.findByEmail(username);
//        if (optional.isEmpty()) {
//            throw new AppBadException("Bad Credentials. Mazgi");
//        }
//
//        ProfileEntity profile = optional.get();
//        return new CustomUserDetail(profile.getId(), profile.getEmail(),
//                profile.getPassword(), profile.getStatus(), profile.getRole());
//    }
//
//}
