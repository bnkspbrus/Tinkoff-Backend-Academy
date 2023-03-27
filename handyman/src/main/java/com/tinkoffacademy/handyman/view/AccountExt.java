package com.tinkoffacademy.handyman.view;

import java.util.List;

public record AccountExt(
        String id,
        String login,
        String email,
        String phone,
        Double latitude,
        Double longitude,
        List<String> skills
) {
}
