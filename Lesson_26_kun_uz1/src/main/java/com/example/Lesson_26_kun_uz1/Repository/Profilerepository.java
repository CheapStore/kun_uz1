package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface Profilerepository extends CrudRepository<ProfileEntity,Integer> {

     Optional<ProfileEntity> findByEmail(String email);

     Optional<ProfileEntity> findByEmailAndPassword(String email, String password);

     Page<ProfileEntity> findAll(Pageable pageable);

     @Transactional
     @Modifying
     @Query("update ProfileEntity p set p.status='ACTIVE' where p.sms=?1 and p.status='REGISTRATION'")
     Integer sms(String kod);



     Optional<ProfileEntity> findBySms(String kod);
     @Transactional
     @Modifying
     @Query("update ProfileEntity p set p.status=?2 where p.id=?1")
     void updateStatus(Integer id, ProfileStatus profileStatus);


     @Transactional
     @Modifying
     @Query("update ProfileEntity p set p.sms=?2 where p.sms=?1 and p.status='REGISTRATION'")
     void updateSMS(String kod, String password);
}
