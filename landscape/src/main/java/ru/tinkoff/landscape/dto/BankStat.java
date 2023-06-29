package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Bank;

import java.time.LocalDateTime;

public record BankStat(
        Bank bank,
        LocalDateTime minTime,
        LocalDateTime maxTime
) {
}
