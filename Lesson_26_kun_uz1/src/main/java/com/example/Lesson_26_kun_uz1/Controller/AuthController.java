package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.AuthDTO;
import com.example.Lesson_26_kun_uz1.DTO.ProfileDTO;

import com.example.Lesson_26_kun_uz1.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
//localhost/8080/auth/login
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Api for login", description = "this api used for authorization")
    private ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO auth) {
        log.trace("Login In Trace");
        log.debug("Login In Debug");
        log.info("Login {} ", auth.getEmail());
        log.warn("Login {} ", auth.getEmail());
        log.error("Login {} ", auth.getEmail());
        return ResponseEntity.ok(authService.auth(auth));
    }


    @PostMapping("/moder")
    private ResponseEntity<ProfileDTO> login1(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(authService.auth(authDTO));
    }


// @PostMapping("/register")
//    private ResponseEntity<Boolean> registration(@RequestBody RegistirationProfileDTO authDTO) {
//        return ResponseEntity.ok(authService.registration(authDTO));
//    }
//
//
//    @GetMapping("/verification/email/{jwt}")
//    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
//        return ResponseEntity.ok(authService.emailVerification(jwt));
//    }


}
