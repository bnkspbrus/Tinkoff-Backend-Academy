package com.tinkoffacademy.handyman.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.tinkoffacademy.handyman.enums.Bank;
import com.tinkoffacademy.handyman.enums.PaymentCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "user_id")
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
