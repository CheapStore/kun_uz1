package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.TypesDTO;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.TypesService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@Slf4j
public class TypesController {

    @Autowired
    public TypesService typesService;

    @PostMapping("/adm/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TypesDTO> create(@RequestBody TypesDTO articleDTO,
                                           HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(typesService.create(articleDTO));
    }


    @PutMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") Integer id,
                                          @RequestBody TypesDTO articleDTO,
                                          HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(typesService.update(id, articleDTO));
    }

    @DeleteMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Integer id,
                                          HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(typesService.delete(id));
    }

    @GetMapping("/language")
    public ResponseEntity<List<TypesDTO>> getListLanguage(@RequestParam(value = "language", defaultValue = "UZ")
                                                              Language language) {
        return ResponseEntity.ok(typesService.getlistLangue(language));
    }

    @GetMapping("/adm/Pagination")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(typesService.pagination(size, page));
    }

    @PostMapping("/mod/create")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<TypesDTO> createModerator(@RequestBody TypesDTO articleDTO,
                                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(typesService.createModerator(articleDTO));
    }




}
