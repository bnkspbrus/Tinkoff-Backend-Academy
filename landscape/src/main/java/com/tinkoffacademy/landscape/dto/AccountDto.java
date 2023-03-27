package com.tinkoffacademy.landscape.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccountDto(
        UUID id,
        String typeName,
        String login,
        String email,
        String phone,
        LocalDateTime creation,
        LocalDateTime updating,
        Double latitude,
        Double longitude
) {
}
