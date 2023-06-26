package com.tinkoffacademy.dto;

import com.tinkoffacademy.handyman.enums.Bank;
import com.tinkoffacademy.handyman.enums.PaymentSystem;
import lombok.Data;

@Data
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
