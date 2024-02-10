package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tags")
@Setter
@Getter
public class TagNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tagName;

    private LocalDateTime createdDate;
}
