package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.EmailSendHistoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.SMSHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EmailHistoryRepository extends CrudRepository<EmailSendHistoryEntity,Integer> {


     Page<ProfileEntity> findAll(Pageable pageable);




     Long countByEmailAndCreatedDateBetween(String email, LocalDateTime from, LocalDateTime to);

     @Query("SELECT count (s) from EmailSendHistoryEntity s where s.email =?1 and s.createdDate between ?2 and ?3")
     Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);

}
