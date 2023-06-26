package com.tinkoffacademy.dto;

import com.tinkoffacademy.landscape.enums.Bank;
import com.tinkoffacademy.landscape.enums.PaymentSystem;
import lombok.Data;

@Data
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
