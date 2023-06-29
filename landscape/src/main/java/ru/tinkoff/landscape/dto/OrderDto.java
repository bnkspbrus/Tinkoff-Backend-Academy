package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Skill;
import ru.tinkoff.landscape.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private Long gardenId;
    private Long userId;
    private Set<Skill> skills;
    private Status status;
    private LocalDateTime creation;
}
