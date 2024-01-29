package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Enums.ProfileStatus;
import com.example.Lesson_26_kun_uz1.Service.ProfileService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServletRequest;
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

    @PostMapping("/adm/crate")
    public ResponseEntity<?> create(@RequestBody CreateProfileDTO dto,
                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return  ResponseEntity.ok(profileService.create(dto));
    }

//    Integer id = (Integer) request.getAttribute("id");
//    ProfileRole role = (ProfileRole) request.getAttribute("role");
//
//        if (!role.equals(ProfileRole.ADMIN)) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//    }


    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(
            @RequestHeader(value = "Authorization") String jwt,
            @PathVariable(value = "id") Integer id,
            @RequestBody UpdateProfileDTO dto,
            HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.update(id, dto));
    }


    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getList(@RequestParam(value = "size", defaultValue = "1") Integer size,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return  ResponseEntity.ok(profileService.getList(size, page));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                       @RequestBody() FilterDTO filter
    ) {
        return ResponseEntity.ok(profileService.filter(page, size, filter));
    }

    @PutMapping("/adm/update")
    public ResponseEntity<?> update(@RequestBody ProfileDTO profileDTO,
                                    HttpServletRequest request) {
        Integer id = HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.updateDetail(id, profileDTO));
    }


}
