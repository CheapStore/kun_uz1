package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.Enums.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBundleService {
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    public String getMessage(String code, Language language){
        return resourceBundleMessageSource.getMessage(code,null,new Locale(language.name()));
    }


}
