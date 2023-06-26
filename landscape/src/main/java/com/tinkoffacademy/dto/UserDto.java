package com.tinkoffacademy.dto;

import com.tinkoffacademy.landscape.enums.Skill;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.EnumSet;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class UserDto extends AccountDto {
    private byte[] photo;
    private EnumSet<Skill> skills;
    private List<UserAccountDto> userAccounts;
}
