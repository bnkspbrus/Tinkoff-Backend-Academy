package com.tinkoffacademy.handyman.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record AccountDto(
        String id,
        String typeName,
        String login,
        String email,
        String phone,
        LocalDateTime creation,
        LocalDateTime updating,
        Double latitude,
        Double longitude,
        List<String> skills
) {
}
