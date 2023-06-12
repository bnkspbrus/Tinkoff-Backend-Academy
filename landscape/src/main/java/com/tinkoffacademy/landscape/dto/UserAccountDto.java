package com.tinkoffacademy.landscape.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tinkoffacademy.landscape.enums.Bank;
import com.tinkoffacademy.landscape.enums.PaymentSystem;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
