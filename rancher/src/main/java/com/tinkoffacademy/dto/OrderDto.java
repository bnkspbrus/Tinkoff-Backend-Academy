package com.tinkoffacademy.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.EnumSet;

@Data
public class OrderDto {
    private Long id;
    private Long gardenId;
    private Long userId;
    private EnumSet<Skill> skills;
    private Status status;
    private LocalDateTime creation;
}
