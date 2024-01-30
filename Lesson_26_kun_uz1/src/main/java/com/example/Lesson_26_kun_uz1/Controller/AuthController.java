package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.AuthDTO;
import com.example.Lesson_26_kun_uz1.DTO.ProfileDTO;
import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    private ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(authService.auth(authDTO));
    }
 @PostMapping("/register")
    private ResponseEntity<Boolean> registration(@RequestBody RegistirationProfileDTO authDTO) {
        return ResponseEntity.ok(authService.registration(authDTO));
    }


    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }


}
