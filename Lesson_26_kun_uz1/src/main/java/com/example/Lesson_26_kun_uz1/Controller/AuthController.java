package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.AuthDTO;
import com.example.Lesson_26_kun_uz1.DTO.ProfileDTO;

import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO auth,
                                             @RequestHeader(value = "Accept-Language", defaultValue = "UZ")
                                             Language language) {
/*      log.trace("Login In Trace");
//        log.debug("Login In Debug");
//        log.info("Login {} ", auth.getEmail());
//        log.warn("Login {} ", auth.getEmail());
*/       log.error("Login {} ", auth.getEmail());
        return ResponseEntity.ok(authService.auth(auth, language));
    }


//    @PostMapping("/login1")
////    @PreAuthorize("hasRole('MODERATOR')")
//    private ResponseEntity<ProfileDTO> loginModerator(@RequestBody AuthDTO authDTO,
//                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
//        return ResponseEntity.ok(authService.auth(authDTO, language));
//    }
//
//    @PostMapping("/user")
//    @PreAuthorize("hasRole('USER')")
//    private ResponseEntity<ProfileDTO> loginUser(@RequestBody AuthDTO authDTO,
//                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
//        return ResponseEntity.ok(authService.auth(authDTO, language));
//    }
//
//
//    @PostMapping("/pub")
//    @PreAuthorize("hasRole('PUBLISHER')")
//    private ResponseEntity<ProfileDTO> loginPublisher(@RequestBody AuthDTO authDTO,
//                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language language) {
//        return ResponseEntity.ok(authService.auth(authDTO, language));
//    }
//
//
//    @PostMapping("/register")
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
