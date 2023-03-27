package com.tinkoffacademy.landscape.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_type_v2")
@Getter
@Setter
public class AccountTypeV2 {
    @Id
    private Integer id;
    @Column(name = "type_name", unique = true, nullable = false)
    private String typeName;
}
