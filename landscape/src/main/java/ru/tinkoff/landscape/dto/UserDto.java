package ru.tinkoff.landscape.dto;

import ru.tinkoff.landscape.enums.Skill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class UserDto extends AccountDto {
    private byte[] photo;
    private Set<Skill> skills;
    private List<UserAccountDto> userAccounts;
}
