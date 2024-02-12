package com.example.Lesson_26_kun_uz1.Service;

import com.example.Lesson_26_kun_uz1.DTO.*;
import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentEntity;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import com.example.Lesson_26_kun_uz1.Entity.RegionEntity;
import com.example.Lesson_26_kun_uz1.Enums.ProfileRole;
import com.example.Lesson_26_kun_uz1.Exp.AppBadException;
import com.example.Lesson_26_kun_uz1.Repository.*;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class ArticleCommentService {
    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private Profilerepository profilerepository;

    @Autowired
    private ArticleCommitCustomRepository customRepository;


    public String create(CreateCommentDTO dto, Integer profileID) {
        Optional<ArticleEntity> article = repository.findById(dto.getArticleId());
        if (article.isEmpty()) {
            log.warn("bunday articleID yo`q comment yaratilmadi!!!");
            throw new AppBadException("bunday articleID yo`q");
        }
        ArticleCommentEntity entity = new ArticleCommentEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setArticleId(dto.getArticleId());
        entity.setContent(dto.getContent());
        entity.setProfileId(profileID);
        articleCommentRepository.save(entity);
        return "create successful.";

    }

    public String update(String articleID, CreateCommentDTO dto, Integer profileID) {

        List<ArticleCommentEntity> byArticleId = articleCommentRepository.findArticleId(articleID);

        if (byArticleId.isEmpty()) {
            log.warn("update no successful");
            throw new AppBadException("update no successful(articleID wrong!!!)");
        }

        ProfileEntity profileEntity = profilerepository.findById(profileID).get();
        ProfileRole role = profileEntity.getRole();


        for (ArticleCommentEntity entity : byArticleId) {
            if (Objects.equals(entity.getProfileId(), profileID) || role.equals(ProfileRole.ADMIN)) {
            entity.setArticleId(dto.getArticleId());
            entity.setContent(dto.getContent());
            entity.setUpdatedDate(LocalDateTime.now());
            articleCommentRepository.save(entity);
        }
        }
        return "update successful:>" + role;
    }

    public String delete(Integer commentID, Integer profileId) {
        ProfileEntity profileEntity = profilerepository.findById(profileId).get();
        ProfileRole role = profileEntity.getRole();
        Optional<ArticleCommentEntity> byId = articleCommentRepository.findById(commentID);
        if (byId.isEmpty()) {
            log.warn("delete not fount");
            throw new AppBadException("delete no (commitID not fount)");
        }
        ArticleCommentEntity entity = byId.get();
        if (Objects.equals(entity.getProfileId(), profileId)||role.equals(ProfileRole.ADMIN)) {
            articleCommentRepository.deleteById(commentID);
        }else {
            return "delete no successful(role is wrong!!!)";
        }
        return "delete successful";

    }

    public List<ArticleCommitGetDTO> getListCommit(String articleID) {
        List<ArticleCommentEntity> articleCommentEntities = articleCommentRepository.findArticleId(articleID);
        List<ArticleCommitGetDTO>list=new LinkedList<>();

        for (ArticleCommentEntity entity : articleCommentEntities) {
            ArticleCommitGetDTO dto=new ArticleCommitGetDTO();
            dto.setId(entity.getId());
            dto.setProfileID(entity.getProfileId());
            dto.setCreateDate(entity.getCreatedDate());
            dto.setUpdateDate(entity.getCreatedDate());
            ProfileEntity profileEntity = profilerepository.findById(entity.getProfileId()).get();
            dto.setProfileName(profileEntity.getName());
            dto.setProfileRole(profileEntity.getRole().name());
            dto.setProfileSurname(profileEntity.getSurname());
            list.add(dto);
        }
        return list;


    }

    public PageImpl<ArticleCommitPaginationDTO> getCommitPagination(Integer page, Integer size) {

        PageRequest pageable = PageRequest.of(page - 1, size);
        Page<ArticleCommentEntity> entityPage = articleCommentRepository.findAll(pageable);
        List<ArticleCommentEntity> entityList = entityPage.getContent();
        long totalElements = entityPage.getTotalElements();
        List<ArticleCommitPaginationDTO> list = new ArrayList<>();

        for (ArticleCommentEntity articleComment : entityList) {
            ArticleCommitPaginationDTO dto=new ArticleCommitPaginationDTO();
            dto.setArticleID(articleComment.getArticleId());
            dto.setContent(articleComment.getContent());
            dto.setProfileID(articleComment.getProfileId());
            dto.setUpdateDate(articleComment.getUpdatedDate());
            dto.setCreateDate(articleComment.getCreatedDate());
            ProfileEntity profileEntity = profilerepository.findById(articleComment.getProfileId()).get();
            dto.setProfileName(profileEntity.getName());
            dto.setProfileSurname(profileEntity.getSurname());
            ArticleEntity articleEntity = repository.findById(articleComment.getArticleId()).get();
            dto.setTitle(articleEntity.getTitle());
            dto.setContent(articleComment.getContent());
            dto.setCommitId(articleComment.getId());
            list.add(dto);
        }
        return new PageImpl<>(list, pageable, totalElements);
    }

    public PageImpl<ArticleCommitFilterPaginationDTO> filter(ArticleCommitFilterDTO dto, Integer page, Integer size) {
        PaginationResultDTO<ArticleCommentEntity> filter = customRepository.filter(page, size, dto);
        List<ArticleCommitFilterPaginationDTO> list = new LinkedList<>();
        for (ArticleCommentEntity entity : filter.getList()) {
            ArticleCommitFilterPaginationDTO dto1=new ArticleCommitFilterPaginationDTO();
            dto1.setArticleID(entity.getArticleId());
            dto1.setUpdateDate(entity.getUpdatedDate());
            dto1.setId(entity.getId());
            dto1.setCreateDate(entity.getCreatedDate());
            dto1.setContent(entity.getContent());
            dto1.setProfileID(entity.getProfileId());
            ArticleEntity articleEntity = repository.findById(entity.getArticleId()).get();
            dto1.setVisible(articleEntity.getVisible());
            list.add(dto1);
        }
        Pageable paging = PageRequest.of(page - 1,size, Sort.Direction.DESC, "createdDate");

        return new PageImpl<>(list, paging,filter.getTotalElement());
    }
}
