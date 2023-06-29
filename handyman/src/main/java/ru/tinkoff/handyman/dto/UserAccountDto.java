package ru.tinkoff.handyman.dto;

import ru.tinkoff.handyman.enums.Bank;
import ru.tinkoff.handyman.enums.PaymentSystem;
import lombok.Data;

@Data
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
