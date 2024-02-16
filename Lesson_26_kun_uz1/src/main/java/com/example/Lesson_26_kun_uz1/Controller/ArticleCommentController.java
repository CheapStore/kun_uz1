package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.Config.CustomUserDetail;
import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitFilterDTO;
import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitFilterPaginationDTO;
import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitPaginationDTO;
import com.example.Lesson_26_kun_uz1.DTO.CreateCommentDTO;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleCommentService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import com.example.Lesson_26_kun_uz1.Util.SpringSecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleComment")
public class ArticleCommentController {
    @Autowired
    private ArticleCommentService articleCommentService;


    @PostMapping("/any/create")
    public ResponseEntity<?> create(@RequestBody CreateCommentDTO dto,
                                    HttpServletRequest request) {
     Integer profileID=SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(articleCommentService.create(dto,profileID));
    }



    @PutMapping("/any/update/{id}")
    public ResponseEntity<?> update(@PathVariable(value = "id") String articleID,
                                    @RequestBody CreateCommentDTO dto,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleCommentService.update(articleID, dto, currentUser.getId()));
    }


    @DeleteMapping("/any/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer commentID,
                                    HttpServletRequest request) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleCommentService.delete(commentID, currentUser.getId()));
    }


    @GetMapping("/getList/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") String articleID) {
        return ResponseEntity.ok(articleCommentService.getListCommit(articleID));
    }


    @GetMapping("/adm/GetCommitPagination")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageImpl<ArticleCommitPaginationDTO>> getCommitPagination(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                                    @RequestParam(value = "size", defaultValue = "1") Integer size
                                                                                    ) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleCommentService.getCommitPagination(page, size));
    }


    @GetMapping("adm/commitFilter")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<PageImpl<ArticleCommitFilterPaginationDTO>> commitFilterPagination(@RequestParam(value = "page") Integer page,
                                                                                              @RequestParam(value = "size") Integer size,
                                                                                              @RequestBody ArticleCommitFilterDTO dto) {
        CustomUserDetail currentUser = SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleCommentService.filter(dto, page, size));
    }

}
