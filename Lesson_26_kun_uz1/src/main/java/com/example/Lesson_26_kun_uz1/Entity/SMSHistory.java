package com.example.Lesson_26_kun_uz1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Entity
@Table(name = "smsHistory")
public class SMSHistory extends CustomEntity {
    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "cod")
    private String cod;

    @Column(name = "reason")
    private String reason;

    @Column(name = "phone_number")
    private String phone;


}
