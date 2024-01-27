package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.CategoryCreateDTO;
import com.example.Lesson_26_kun_uz1.DTO.CategoryDTO;
import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.CategoryService;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
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

    @PostMapping("/create")
    public ResponseEntity<CategoryCreateDTO> create(@RequestBody CategoryCreateDTO dto,
                                                    @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(categoryService.create(dto)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") Integer id,
                                    @RequestBody CategoryCreateDTO categoryCreateDTO,
                                    @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(categoryService.update(id, categoryCreateDTO)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id,
                                    @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(categoryService.delete(id)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/Pagination")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(categoryService.pagination(size, page)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @GetMapping("/language")
    private ResponseEntity<?> getLanguage(@RequestParam(value = "language") String language) {

        return ResponseEntity.ok(categoryService.getLanguage(language));
    }


}
