package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.DTO.ArticleCommitFilterDTO;
import com.example.Lesson_26_kun_uz1.DTO.PaginationResultDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleCommentEntity;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleCommitCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public PaginationResultDTO<ArticleCommentEntity> filter(Integer page, Integer size, ArticleCommitFilterDTO dto) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        //Integer id;
        //LocalDateTime createDateFrom;
        //LocalDateTime createDateTo;
        //Integer profileID;
        //String articleID;

        if (dto.getId() != null) {
            builder.append("and id=:id ");
            params.put("id", dto.getId());
        }

        if (dto.getArticleID()!=null){
            builder.append("and articleId=:articleId");
            params.put("articleId",dto.getArticleID());
        }

        if (dto.getProfileID()!=null){
            builder.append("and profileId=:profileId");
            params.put("profileId",dto.getProfileID());
        }
        if (dto.getCreateDateFrom() != null && dto.getCreateDateTo() != null) {
            LocalDateTime fromDate = LocalDateTime.of(dto.getCreateDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(dto.getCreateDateTo(), LocalTime.MAX);
            builder.append(" and createdDate  between : fromDate and : toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (dto.getCreateDateFrom() != null) {
            LocalDateTime fromDate = LocalDateTime.of(dto.getCreateDateFrom(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(dto.getCreateDateFrom(), LocalTime.MAX);
            builder.append(" and createdDate between : fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (dto.getCreateDateTo() != null) {
            builder.append(" and createdDate <= toDate ");
            params.put("toDate", dto.getCreateDateTo());
        }

        StringBuilder sql=new StringBuilder("from ArticleCommentEntity a where 1=1 ");
        sql.append(builder);



        StringBuilder countBuilder=new StringBuilder("select count(a) from ArticleCommentEntity a where 1=1 ");
        countBuilder.append(builder);



        Query select = entityManager.createQuery(sql.toString());
        select.setMaxResults(size);
        select.setFirstResult(page);

        Query countQuery=entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            select.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());

        }
        List<ArticleCommentEntity> entityList = select.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();


        return new PaginationResultDTO<>(entityList, totalElements);
    }
}
