package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.SMSHistory;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SMSHistoryRepository extends CrudRepository<SMSHistory,Integer> {


     Page<ProfileEntity> findAll(Pageable pageable);

}
