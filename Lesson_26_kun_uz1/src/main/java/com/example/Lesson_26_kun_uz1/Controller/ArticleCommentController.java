package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitFilterDTO;
import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitFilterPaginationDTO;
import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitPaginationDTO;
import com.example.Lesson_26_kun_uz1.DTO.CreateCommentDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleCommentService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleComment")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;


    @PostMapping("/any/create")
    public ResponseEntity<?> create(@RequestBody CreateCommentDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR, ProfileRole.ADMIN, ProfileRole.PUBLISHER, ProfileRole.USER);
        return ResponseEntity.ok(articleCommentService.create(dto, profileId));
    }

    @PutMapping("/any/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String articleID,
                                    @RequestBody CreateCommentDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN, ProfileRole.USER, ProfileRole.PUBLISHER, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleCommentService.update(articleID, dto, profileId));
    }


    @DeleteMapping("/any/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer commentID,
                                    HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN, ProfileRole.USER, ProfileRole.PUBLISHER, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleCommentService.delete(commentID, profileId));
    }


    @GetMapping("/getList/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") String articleID) {
        return ResponseEntity.ok(articleCommentService.getListCommit(articleID));
    }


    @GetMapping("/adm/GetCommitPagination")
    public ResponseEntity<PageImpl<ArticleCommitPaginationDTO>> getCommitPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                                    @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                                                    HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleCommentService.getCommitPagination(page, size));
    }


    @GetMapping("adm/commitFilter")
    private ResponseEntity<PageImpl<ArticleCommitFilterPaginationDTO>> commitFilterPagination(@RequestParam(value = "page") Integer page,
                                                                                              @RequestParam(value = "size") Integer size,
                                                                                              @RequestBody ArticleCommitFilterDTO dto,
                                                                                              HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(articleCommentService.filter(dto, page, size));
    }

}
