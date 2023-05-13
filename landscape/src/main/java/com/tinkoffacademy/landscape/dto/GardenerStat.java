package com.tinkoffacademy.landscape.dto;

public record GardenerStat(
        String login,
        double minFieldSize,
        double maxFieldSize,
        double avgFieldSize
) {
}
