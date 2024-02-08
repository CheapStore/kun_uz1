package com.example.Lesson_26_kun_uz1.Entity;

import com.example.Lesson_26_kun_uz1.Enums.Language;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Entity
@Table(name = "region")
public class RegionEntity extends BaseEntity {

    @GeneratedValue
    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "name_Uz")
    private String nameUz;

    @Column(name = "name_Ru")
    private String nameRu;

    @Column(name = "name_En")
    private String nameEn;
}