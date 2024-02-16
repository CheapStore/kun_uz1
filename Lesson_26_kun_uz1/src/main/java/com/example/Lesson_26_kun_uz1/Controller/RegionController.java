package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.RegionDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.RegionService;
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
@RequestMapping("/region")
@Slf4j
public class RegionController {
    @Autowired
    public RegionService service;

    @PostMapping("/adm/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto)
//                                            HttpServletRequest request)
    {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(service.create(dto));
    }


    @PutMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") Integer id,
                                          @RequestBody RegionDTO dto,
                                          HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return  ResponseEntity.ok(service.update(id, dto));
    }


    @DeleteMapping("/adm/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Boolean> delete(@PathVariable(value = "id") Integer id,
                                          HttpServletRequest request) {

        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(service.delete(id));
    }


    @GetMapping("/language")
    public ResponseEntity<List<RegionDTO>> getLanguage(@RequestParam(value = "lan") String language) {
        return ResponseEntity.ok(service.getLanguage(language));
    }

    @GetMapping("/adm/Pagination")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> pagination(@RequestParam(value = "size") Integer size,
                                         @RequestParam(value = "page") Integer page,
                                         HttpServletRequest request) {

         CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(service.pagination(size, page)) ;
    }

//    @GetMapping("/change")
//    @PreAuthorize("hashRole('ADMIN')")
//    public ResponseEntity<?>change(){
//        return ResponseEntity.ok("DONE");
//    }
//
//    @GetMapping("/change2")
//    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
//    public ResponseEntity<String> change2() {
//        return ResponseEntity.ok("DONE");
//    }




}
