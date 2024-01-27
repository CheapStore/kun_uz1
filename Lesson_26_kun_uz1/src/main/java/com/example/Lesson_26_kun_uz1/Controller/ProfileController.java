package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Service.ProfileService;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/crate")
    public ResponseEntity<?> create(@RequestBody CreateProfileDTO dto,
                                    @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(profileService.create(dto)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @RequestHeader(value = "Authorization") String jwt,
            @PathVariable(value = "id") Integer id,
            @RequestBody UpdateProfileDTO dto) {

        return JWTUtil.check(jwt) ? ResponseEntity.ok(profileService.update(id, dto)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }


    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getList(@RequestParam(value = "size", defaultValue = "1") Integer size,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(profileService.getList(size, page)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();


    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                       @RequestBody() FilterDTO filter
    ) {



        return ResponseEntity.ok(profileService.filter(page,size, filter));
    }


}
