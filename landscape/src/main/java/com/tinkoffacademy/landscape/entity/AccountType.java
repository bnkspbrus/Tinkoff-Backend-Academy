package com.tinkoffacademy.landscape.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(indexes = @Index(columnList = "name", unique = true))
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String name;
}
