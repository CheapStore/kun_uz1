package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.RegionDTO;
import com.example.Lesson_26_kun_uz1.Entity.BaseEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    public RegionService service;

    @PostMapping("/creat")
    public ResponseEntity<RegionEntity>create(@RequestBody RegionDTO dto){
      return ResponseEntity.ok(service.create(dto));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Boolean>update(@PathVariable(value = "id")Integer id,
                                            @RequestBody RegionDTO dto){
        return ResponseEntity.ok(service.update(id,dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>delete(@PathVariable(value = "id")Integer id){
        return ResponseEntity.ok(service.delete(id));
    }


    @GetMapping("/language")
    public ResponseEntity<List<RegionDTO>>getLanguage(@RequestParam(value = "lan") Language language){
        return ResponseEntity.ok(service.getLanguage(language));
    }

    @GetMapping("/Pagination")
    private ResponseEntity<?>pagination(@RequestParam(value = "size")Integer size,
                                        @RequestParam(value = "page")Integer page){
        return  ResponseEntity.ok(service.pagination(size,page));
    }

}
