package com.tinkoffacademy.landscape.entity;

import com.tinkoffacademy.landscape.enums.AccountType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @CreationTimestamp
    private LocalDateTime creation;
    @UpdateTimestamp
    private LocalDateTime updating;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
}
