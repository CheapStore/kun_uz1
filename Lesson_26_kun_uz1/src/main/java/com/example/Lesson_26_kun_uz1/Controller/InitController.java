package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Service.InitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/init")
@Slf4j
public class InitController {
    //localhost:8080/init/admin

    @Autowired
    private InitService initService;

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String intAdmin(){
//        initService.initAdmin();
        return "DONE";
    }




}
