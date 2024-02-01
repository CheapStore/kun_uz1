package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "email_send_history")
@Setter
@Getter

public class EmailSendHistoryEntity extends CustomEntity {
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "sabab",nullable = false)
    private String sabab;


    @Column(name = "cod",nullable = false)
    private String cod;




}
