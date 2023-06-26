package com.tinkoffacademy.dto;

import com.tinkoffacademy.landscape.enums.Bank;

import java.time.LocalDateTime;

public record BankStat(
        Bank bank,
        LocalDateTime minTime,
        LocalDateTime maxTime
) {
}
