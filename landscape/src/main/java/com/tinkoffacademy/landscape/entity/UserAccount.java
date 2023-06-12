package com.tinkoffacademy.landscape.entity;

import com.tinkoffacademy.landscape.enums.Bank;
import com.tinkoffacademy.landscape.enums.PaymentSystem;
import lombok.*;

import javax.persistence.*;

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
    private User user;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Bank bank;
    @Column(nullable = false)
    private Long cardId;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;
}
