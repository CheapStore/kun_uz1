package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ProfileService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Slf4j
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/crate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody CreateProfileDTO dto,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(profileService.create(dto));
    }

//    Integer id = (Integer) request.getAttribute("id");
//    ProfileRole role = (ProfileRole) request.getAttribute("role");
//
//        if (!role.equals(ProfileRole.ROLE_ADMIN)) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//    }


    @PutMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(
            @RequestHeader(value = "Authorization") String jwt,
            @PathVariable(value = "id") Integer id,
            @RequestBody UpdateProfileDTO dto,
            HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(profileService.update(id, dto));
    }


    @GetMapping("/adm/pagination")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageImpl<ProfileDTO>> getList(@RequestParam(value = "size", defaultValue = "1") Integer size,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(profileService.getList(size, page));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                       @RequestBody() FilterDTO filter
    ) {
        return ResponseEntity.ok(profileService.filter(page, size, filter));
    }

    @PutMapping("/adm/updateDetail")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProfileDTO profileDTO,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(profileService.updateDetail(currentUser.getId(), profileDTO));
    }


}
