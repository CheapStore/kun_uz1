package com.example.Lesson_26_kun_uz1.Service;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;


@Component
public class CommandLinAppStartRunner implements CommandLineRunner {
    @Autowired
    private InitService initService;

    @Autowired
    private DataSource dataSource;



//    @Override
//    public void run(String... args) throws Exception {
//        initService.initAdmin();
//    }
         @Override
    public void run(String... args)throws Exception{
        Flyway.configure().baselineOnMigrate(true).dataSource(dataSource).load().migrate();
         }


}
