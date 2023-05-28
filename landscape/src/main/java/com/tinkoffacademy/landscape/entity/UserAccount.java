package com.tinkoffacademy.landscape.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tinkoffacademy.landscape.enums.Bank;
import com.tinkoffacademy.landscape.enums.PaymentCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JsonBackReference
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Bank bank;
    @Column(nullable = false)
    private Long cardId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentCard paymentCard;
}
