package com.tinkoffacademy.landscape.dto;

import com.tinkoffacademy.landscape.enums.Skill;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AccountDto {
    private byte[] photo;
    private Set<Skill> skills;
    private List<UserAccountDto> userAccounts;
}
