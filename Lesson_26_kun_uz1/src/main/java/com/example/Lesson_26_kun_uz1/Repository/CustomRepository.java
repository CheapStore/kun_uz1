package com.example.Lesson_26_kun_uz1.Repository;

import com.example.Lesson_26_kun_uz1.DTO.FilterDTO;
import com.example.Lesson_26_kun_uz1.DTO.PaginationResultDTO;
import com.example.Lesson_26_kun_uz1.Entity.ProfileEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomRepository {
    @Autowired
    private EntityManager entityManager;

    //Filter (name ,surname ,phone ,role,created_date_from,created_date_to)
    public PaginationResultDTO<ProfileEntity> filter(Integer page,Integer size, FilterDTO filter) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filter.getId() != null) {
            builder.append("and id=:id ");
            params.put("id", filter.getId());
        }
        if (filter.getName() != null) {
            builder.append("and lower(surname) like :surname ");
            params.put("surname", filter.getSurname().toLowerCase());
        }
        if (filter.getSurname() != null) {
            builder.append("and lower(name) like :name ");
            params.put("name", filter.getName().toLowerCase());
        }
        if (filter.getPhone() != null) {
            builder.append("and phone=:phone ");
            params.put("phone", filter.getPhone());
        }
        if (filter.getRole() != null) {
            builder.append("and role=:role ");
            params.put("role", filter.getRole());
        }
        StringBuilder stringBuilder = new StringBuilder("from ProfileEntity p where 1=1 ");
        stringBuilder.append(builder);

        StringBuilder countBuilder = new StringBuilder("Select count(p) FROM ProfileEntity p where 1=1 ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(stringBuilder.toString());
        selectQuery.setMaxResults(size);
        selectQuery.setFirstResult(page);

        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ProfileEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();
        return new PaginationResultDTO<ProfileEntity>(entityList,totalElements);
    }
}
