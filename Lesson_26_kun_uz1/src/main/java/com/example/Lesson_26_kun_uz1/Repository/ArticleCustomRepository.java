package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.DTO.ArticleFilterDTO;
import com.example.Lesson_26_kun_uz1.DTO.PaginationResultDTO;
import com.example.Lesson_26_kun_uz1.Entity.ArticleEntity;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
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
public class ArticleCustomRepository {
    @Autowired
    private EntityManager entityManager;


    public PaginationResultDTO<ArticleEntity> filter(Integer page, Integer size, ArticleFilterDTO filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getId() != null) {
            builder.append("and id=:id ");
            params.put("id", filter.getId());
        }
        if (filter.getCategory_id() != null) {
            builder.append("and categoryId =: categoryId ");
            params.put("categoryId", filter.getCategory_id());
        }
        if (filter.getRegion_id() != null) {
            builder.append("and regionId =: regionId ");
            params.put("regionId", filter.getRegion_id());
        }
        if (filter.getTitle() != null) {
            builder.append("and lower(title) like :title ");
            params.put("title", filter.getTitle().toLowerCase());
        }
        if (filter.getStatus() != null) {
            builder.append("and status =:status ");
            params.put("status", filter.getStatus());
        }
        if (filter.getModerator_id() != null) {
            builder.append("and moderatorId =: moderatorId ");
            params.put("moderatorId", filter.getModerator_id());
        }
        if (filter.getCrated_date_from() != null && filter.getCreated_date_to() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getCrated_date_from(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getCreated_date_to(), LocalTime.MAX);
            builder.append(" and createdDate  between : fromDate and : toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getCrated_date_from() != null) {
            LocalDateTime fromDate = LocalDateTime.of(filter.getCrated_date_from(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(filter.getCrated_date_from(), LocalTime.MAX);
            builder.append(" and createdDate between : fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        } else if (filter.getCreated_date_to() != null) {
            builder.append(" and createdDate <= toDate ");
            params.put("toDate", filter.getCreated_date_to());
        }

        StringBuilder stringBuilder = new StringBuilder("from ArticleEntity a where 1=1 ");
        stringBuilder.append(builder);


        Query selectQuery = entityManager.createQuery(stringBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult(page);

        StringBuilder countBuilder = new StringBuilder("Select count(a) FROM ArticleEntity a where 1=1 ");
        countBuilder.append(builder);

        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());

        }
        List<ArticleEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PaginationResultDTO<ArticleEntity>(entityList, totalElements);


    }
}
