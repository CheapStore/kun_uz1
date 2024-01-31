package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.EmailSendHistoryEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.SMSHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface EmailHistoryRepository extends CrudRepository<EmailSendHistoryEntity,Integer> {


     Page<ProfileEntity> findAll(Pageable pageable);

}
