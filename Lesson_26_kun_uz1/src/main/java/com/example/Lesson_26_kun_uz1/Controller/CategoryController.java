package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.CategoryCreateDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.CategoryService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/adm/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryCreateDTO> create(@RequestBody CategoryCreateDTO dto,
                                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody CategoryCreateDTO categoryCreateDTO,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(categoryService.update(id, categoryCreateDTO));

    }


    @DeleteMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(categoryService.delete(id)) ;
    }

    @GetMapping("/adm/Pagination")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(categoryService.pagination(size, page));
    }


    @GetMapping("/language")
    private ResponseEntity<?> getLanguage(@RequestParam(value = "language") String language) {
        return ResponseEntity.ok(categoryService.getLanguage(language));
    }


}
