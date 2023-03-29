package com.tinkoffacademy.rancher.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AccountDto(
        String login,
        String email,
        String phone,
        Double latitude,
        Double longitude,
        List<String> jobs
) {
}
