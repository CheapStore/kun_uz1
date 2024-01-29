package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.CategoryCreateDTO;
import com.example.Lesson_26_kun_uz1.DTO.CategoryDTO;
import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.CategoryService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/adm/create")
    public ResponseEntity<CategoryCreateDTO> create(@RequestBody CategoryCreateDTO dto,
                                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.create(dto));
    }

    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody CategoryCreateDTO categoryCreateDTO,
                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return  ResponseEntity.ok(categoryService.update(id, categoryCreateDTO));

    }


    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return  ResponseEntity.ok(categoryService.delete(id)) ;
    }

    @GetMapping("/adm/Pagination")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.pagination(size, page));
    }


    @GetMapping("/language")
    private ResponseEntity<?> getLanguage(@RequestParam(value = "language") String language) {
        return ResponseEntity.ok(categoryService.getLanguage(language));
    }


}
