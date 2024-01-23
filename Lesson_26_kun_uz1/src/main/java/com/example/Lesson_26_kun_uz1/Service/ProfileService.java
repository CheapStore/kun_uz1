package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Repository.Profilerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {


    @Autowired
    private Profilerepository profilerepository;
}



