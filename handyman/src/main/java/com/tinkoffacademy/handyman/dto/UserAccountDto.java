package com.tinkoffacademy.handyman.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinkoffacademy.handyman.enums.Bank;
import com.tinkoffacademy.handyman.enums.PaymentSystem;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
