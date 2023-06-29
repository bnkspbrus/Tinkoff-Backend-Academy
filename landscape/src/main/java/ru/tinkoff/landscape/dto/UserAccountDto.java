package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Bank;
import ru.tinkoff.landscape.enums.PaymentSystem;
import lombok.Data;

@Data
public class UserAccountDto {
    private Long id;
    private Bank bank;
    private Long cardId;
    private PaymentSystem paymentSystem;
}
