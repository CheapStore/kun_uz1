package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "types")
public class TypesEntity extends BaseEntity {
    @Column(name = "order_number")
    private Long orderNumber;
    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name_ru")
    private String nameRu;
}

