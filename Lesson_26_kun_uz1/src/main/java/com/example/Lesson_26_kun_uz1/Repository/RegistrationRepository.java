package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface RegistrationRepository extends CrudRepository<ProfileEntity,Integer> {
}
