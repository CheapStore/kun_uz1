package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.RegistirationProfileDTO;
import com.example.Lesson_26_kun_uz1.Service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sms")
public class SMSController {

    @Autowired
    RegistrationService service;

    @PostMapping("/create")
    public ResponseEntity<String> registration(@RequestBody RegistirationProfileDTO registirationProfileDTO) {
        return ResponseEntity.ok(service.register(registirationProfileDTO));
    }

    @PostMapping ("/sms")
    public ResponseEntity<String> updateStatus(@RequestParam(value = "kod")String kod) {
        return ResponseEntity.ok(service.updateStatus(kod));
    }
}
