package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Profilerepository extends CrudRepository<ProfileEntity,Integer> {

     Optional<ProfileEntity>findByEmail(String email);
     Optional<ProfileEntity>findByEmailAndPassword(String email,String password);

}
