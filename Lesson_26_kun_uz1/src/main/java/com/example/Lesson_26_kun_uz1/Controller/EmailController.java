package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private AuthService authService;


    @PostMapping("/create")
    private ResponseEntity<Boolean> registration(@RequestBody RegistirationProfileDTO authDTO) {
        return ResponseEntity.ok(authService.registration(authDTO));
    }


    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }


    @GetMapping("/get")
    public ResponseEntity<List<ProfileEntity>> getAll() {
        return ResponseEntity.ok(authService.getAll());
    }
}
