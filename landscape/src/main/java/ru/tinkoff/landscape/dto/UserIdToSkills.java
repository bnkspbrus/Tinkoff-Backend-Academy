package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Skill;

import java.util.Set;

public record UserIdToSkills(
        Long userId,
        Set<Skill> skills
) {
}
