package ru.tinkoff.landscape.dto;

public record GardenerStat(
        String login,
        double minFieldSize,
        double maxFieldSize,
        double avgFieldSize
) {
}
