package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.AttachDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.AttachService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.PathIterator;
import java.net.http.HttpRequest;

@Slf4j
@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String filename = attachService.saveToSystem(file);
        return ResponseEntity.ok(filename);
    }

    @PostMapping("/adm/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file,
                                            HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN, ProfileRole.MODERATOR,
                ProfileRole.USER, ProfileRole.PUBLISHER);
        AttachDTO dto = attachService.save(file);
        return ResponseEntity.ok().body(dto);
    }


    @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }


    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }


    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        return attachService.download(fileName);
    }


    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl<AttachDTO>> pagination(@RequestParam Integer size,
                                                          @RequestParam Integer page,
                                                          HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.pagination(size, page));
    }


    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id,
                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(attachService.delete(id));
    }

}
