package com.tinkoffacademy.dto;

public record GardenerStat(
        String login,
        double minFieldSize,
        double maxFieldSize,
        double avgFieldSize
) {
}
