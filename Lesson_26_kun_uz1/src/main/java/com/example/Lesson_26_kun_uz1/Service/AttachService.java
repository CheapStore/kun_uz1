package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.AttachDTO;
import com.example.Lesson_26_kun_uz1.Entity.AttachEntity;
import com.example.Lesson_26_kun_uz1.Entity.BaseEntity;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.AttachRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AttachService {
    @Value("${server.url}")
    public String serverURL;

    @Autowired
    private AttachRepository attachRepository;

    public String saveToSystem(MultipartFile file) { // mazgi.png
        try {
            File folder = new File("attaches");
            if (!folder.exists()) {
                folder.mkdir();
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get("attaches/" + file.getOriginalFilename()); // attaches/mazgi.png
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    public byte[] loadImage(String fileName) { // zari.jpg
//        BufferedImage originalImage;
//        try {
//            originalImage = ImageIO.read(new File("attaches/" + fileName));
//            // attaches/zari.jpg
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(originalImage, "png", baos);
//            baos.flush();
//
//            byte[] imageInByte;
//            imageInByte = baos.toByteArray();
//            baos.close();
//            return imageInByte;
//        } catch (Exception e) {
//            return new byte[0];
//        }
//    }
    public byte[] loadImage(String fileName) { // zari.jpg
        BufferedImage originalImage;
        try {
            originalImage = ImageIO.read(new File("attaches/" + fileName));
            // attaches/zari.jpg

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "png", baos);
            baos.flush();

            byte[] imageInByte;
            imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (Exception e) {
            return new byte[0];
        }
    }


    public byte[] open_general(String fileName) {
        byte[] data;
        try {
            Path file = Paths.get("attaches/" + fileName);
            data = Files.readAllBytes(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);

        return year + "/" + month + "/" + day; // 2024/4/23
    }

    public String getExtension(String fileName) { // mp3/jpg/npg/mp4.....
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(lastIndex + 1);
    }

    public AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setUrl(serverURL + "/attach/open_general/" + entity.getId() + "." + entity.getExtension());
        return dto;
    }

    public AttachDTO save(MultipartFile file) { // mazgi.png
        try {
            String pathFolder = getYmDString(); // 2022/04/23
            File folder = new File("uploads/" + pathFolder);
            if (!folder.exists()) { // uploads/2022/04/23
                folder.mkdirs();
            }
            String key = UUID.randomUUID().toString(); // dasdasd-dasdasda-asdasda-asdasd
            String extension = getExtension(file.getOriginalFilename()); // mp3/jpg/npg/mp4

            byte[] bytes = file.getBytes();
            Path path = Paths.get("uploads/" + pathFolder + "/" + key + "." + extension);
            //                         uploads/2022/04/23/dasdasd-dasdasda-asdasda-asdasd.jpg
            //                         uploads/ + Path + id + extension
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setSize(file.getSize());
            entity.setExtension(extension);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setCreatedData(LocalDateTime.now());
            entity.setId(key);
            entity.setPath(pathFolder);

            attachRepository.save(entity);

            return toDTO(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    AttachEntity get(String id) {
        return attachRepository.findById(id).orElseThrow(() -> new AppBadException("File not found"));
    }

    public ResponseEntity download(String attachId) {
        try {
            String id = attachId.substring(0, attachId.lastIndexOf("."));

            AttachEntity entity = get(id);

            Path file = Paths.get("uploads/" + entity.getPath() + "/" + attachId);

            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + entity.getOriginalName() + "\"").body(resource);
            } else {
                log.warn("Could not read the file! {}","123456") ;
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    public PageImpl<AttachDTO> pagination(Integer size, Integer page) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<AttachEntity> studentPage = attachRepository.findAll(pageable);
        List<AttachEntity> entityList = studentPage.getContent();
        long totalElements = studentPage.getTotalElements();
        List<AttachDTO> dtoList = new ArrayList<>();

        for (AttachEntity courseEntity : entityList) {
            dtoList.add(dto(courseEntity));
        }
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    public AttachDTO dto(AttachEntity entity) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setExtension(entity.getExtension());
        attachDTO.setId(entity.getId());
        attachDTO.setCreatedData(entity.getCreatedData());
        attachDTO.setSize(entity.getSize());
        return attachDTO;
    }

    public Boolean delete(String id) {
        AttachEntity attachEntity = get(id);
        new File("uploads/" + attachEntity.getPath() + "/" + attachEntity.getId() + "." + attachEntity.getExtension()).deleteOnExit();
        attachRepository.deleteById(id);
        return true;
    }
}
