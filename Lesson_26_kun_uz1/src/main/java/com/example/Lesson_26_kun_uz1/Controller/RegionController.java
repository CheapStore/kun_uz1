package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.JwtDTO;
import com.example.Lesson_26_kun_uz1.DTO.RegionDTO;
import com.example.Lesson_26_kun_uz1.Entity.BaseEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.RegionService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    public RegionService service;

    @PostMapping("/adm")
    public ResponseEntity<RegionEntity> create(@RequestBody RegionDTO dto,
                                               HttpServletRequest request) {

        HttpRequestUTIL.getProfileId(request,ProfileRole.ADMIN,ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.create(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") Integer id,
                                          @RequestBody RegionDTO dto,
                                          @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(service.update(id, dto)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Integer id,
                                          @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(service.delete(id)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }


    @GetMapping("/language")
    public ResponseEntity<List<RegionDTO>> getLanguage(@RequestParam(value = "lan") String language) {
        return ResponseEntity.ok(service.getLanguage(language));
    }

    @GetMapping("/Pagination")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         @RequestHeader(value = "Authorization") String jwt) {
        return JWTUtil.check(jwt) ? ResponseEntity.ok(service.pagination(size, page)) :
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
