package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Skill;

import java.util.List;
import java.util.Set;

public record Report(
        Set<Skill> skills,
        List<UserIdToSkills> userToSkills,
        boolean isDeficit
) {
}
