package com.example.Lesson_26_kun_uz1.Controller;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Enums.Language;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Service.ArticleService;
import com.example.Lesson_26_kun_uz1.Util.HttpRequestUTIL;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    //1
    @PostMapping("/moder")
    @Operation(summary = "create", description = "Article create")
    public ResponseEntity<?> create(@RequestBody CreateArticleDTO dto,
                                    HttpServletRequest request) {
        Integer profileId = HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, profileId));
    }

    //2
    @PutMapping("/moder/{id}")
    @Operation(summary = "API article update", description = "article update(id)")
    public ResponseEntity<String> update(@RequestBody UpdateArticleDTO dto,
                                         @PathVariable(value = "id") String id,
                                         HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.update(id, dto));
    }


    //3
    @PutMapping("/moder/updateVisible/{id}")
    @Operation(summary = "Update", description = "Update visible  (visible=false)")
    public ResponseEntity<Boolean> updateVisible(@PathVariable(value = "id") String id,
                                                 HttpServletRequest request) {
        HttpRequestUTIL.getProfileId(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(articleService.updateVisible(id));
    }


    //4
    @PutMapping("/pub/updateStatus/{id}")
    @Operation(summary = "update Status", description = "update Status (PUBLISHED)")
    public ResponseEntity<Boolean> update(@PathVariable(value = "id") String id,
                                          HttpServletRequest request) {
        Integer publisherID = HttpRequestUTIL.getProfileId(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.updateStatus(id, publisherID));
    }

    //5
    @GetMapping("/ArticleByTypes")
    @Operation(summary = "Article By Types", description = "Get Last 5 Article By Types  ordered_by_created_date")
    public ResponseEntity<List<ArticleDTO>> ArticleByTypes(@RequestParam(value = "id") Integer id,
                                                           @RequestParam(value = "size") Integer size) {
        return ResponseEntity.ok(articleService.ArticleByTypes(id, size));
    }

    //6
    @GetMapping("/ArticleSwitchId/{id}")
    @Operation(summary = "Article By Types", description = "Get Last 3 Article By Types  ordered_by_created_date")
    public ResponseEntity<List<ArticleDTO>> ArticleSwitchId(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(articleService.ArticleSwitchId(id));
    }


    //7

    @GetMapping("/getLanguage/{id}")
    @Operation(summary = "Articles witch", description = "Get Last 8  Articles witch id not included in given list.")
    public ResponseEntity<ReturningData> getLanguage(@PathVariable(value = "id") String id,
                                                     @RequestParam(value = "lan", defaultValue = "UZ") Language lan) {
        return ResponseEntity.ok(articleService.getLanguage(id, lan));
    }

    //8
    @GetMapping("/ArticleByTypesAndExceptArticleId/{id}")
    @Operation(summary = "Article By Types And Except ArticleId", description = "Get Article By Id And Lang")
    public ResponseEntity<List<ArticleDTO>> ArticleByTypesAndExceptArticleId(@PathVariable(value = "id") Integer typesID,
                                                                             @RequestParam(value = "articleID") String articleID) {
        return ResponseEntity.ok(articleService.ArticleByTypesAndExceptArticleId(typesID, articleID));
    }

    //9
    @GetMapping("/GetReaderArticle")
    @Operation(summary = "Get Reader Article", description = "Get Last Article By Types and except given article id.")
    public ResponseEntity<List<String>> GetReaderArticle() {
        return ResponseEntity.ok(articleService.GetReaderArticle());
    }

    //10
    @GetMapping("/ArticleByTypesIDAndByRegionID/{id}")
    public ResponseEntity<List<ReturningData>> articleIdAndRegionID(@PathVariable(value = "id") Integer articleTypesId,
                                                                    @RequestParam(value = "regionID") Integer regionID) {
        return ResponseEntity.ok(articleService.articleIdAndRegionID(articleTypesId, regionID));
    }

    //11
    @GetMapping("/regionIdAndLan/{id}")
    public ResponseEntity<?> regionIdAndLan(@PathVariable(value = "id") Integer regionID,
                                            @RequestParam(value = "lan") Language lan,
                                            @RequestParam(value = "size") Integer size,
                                            @RequestParam(value = "page") Integer page) {
        return ResponseEntity.ok(articleService.regionIdAndLan(regionID, lan, page, size));
    }

    //12
    @GetMapping("/CategoryIDAndLan/{id}")
    public ResponseEntity<?> regionIdAndLan(@PathVariable(value = "id") Integer categoryID,
                                            @RequestParam(value = "lan") Language lan) {
        return ResponseEntity.ok(articleService.CategoryIDAndLan(categoryID, lan));
    }

    //13
    @GetMapping("/categoryIdAndLan/{id}")
    public ResponseEntity<?> categoryIdAndLan(@PathVariable(value = "id") Integer categoryId,
                                              @RequestParam(value = "lan") Language lan,
                                              @RequestParam(value = "size") Integer size,
                                              @RequestParam(value = "page") Integer page) {
        return ResponseEntity.ok(articleService.categoryIdAndLan(categoryId, lan, page, size));
    }
    //14

    @PutMapping("/viewCount/{id}")
    public ResponseEntity<?> viewCountPlus(@PathVariable(value = "id") String articleID) {
        return ResponseEntity.ok(articleService.viewCountPlus(articleID));
    }

    //15
    @PutMapping("/ShareViewCount/{id}")
    public ResponseEntity<?> ShareViewCount(@PathVariable(value = "id") String articleID) {
        return ResponseEntity.ok(articleService.ShareViewCount(articleID));
    }

    //18

    @GetMapping("/pub/filter")
    public ResponseEntity<PageImpl<ArticleFilterDTO>> articleFilter(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                    @RequestParam(value = "size", defaultValue = "1") Integer size,
                                                                    @RequestBody() ArticleFilterDTO filter,
                                                                    HttpServletRequest request
    )
    {
        HttpRequestUTIL.getProfileId(request,ProfileRole.PUBLISHER);
        return ResponseEntity.ok(articleService.filter(page, size, filter));
    }


}
