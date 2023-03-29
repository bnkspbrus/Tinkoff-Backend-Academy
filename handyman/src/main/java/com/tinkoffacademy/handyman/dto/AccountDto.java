package com.tinkoffacademy.handyman.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AccountDto(
        String login,
        String email,
        String phone,
        Double latitude,
        Double longitude,
        List<String> skills
) {
}
