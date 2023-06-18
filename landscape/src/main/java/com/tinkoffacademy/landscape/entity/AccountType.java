package com.tinkoffacademy.landscape.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "account_types")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String name;
}
