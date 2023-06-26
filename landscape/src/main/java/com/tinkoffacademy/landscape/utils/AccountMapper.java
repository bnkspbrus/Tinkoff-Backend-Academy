package com.tinkoffacademy.landscape.utils;

import com.tinkoffacademy.dto.AccountDto;
import com.tinkoffacademy.dto.GardenerDto;
import com.tinkoffacademy.dto.UserDto;
import com.tinkoffacademy.landscape.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * AccountMapper is a class that maps Account to AccountDto and vice versa.
 *
 * @see AccountDto
 * @see Account
 */
@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper modelMapper;

    /**
     * Maps AccountDto to Account using ModelMapper.
     */
    public Account mapToAccount(AccountDto accountDto) {
        if (accountDto instanceof UserDto) {
            return modelMapper.map(accountDto, User.class);
        }
        if (accountDto instanceof GardenerDto) {
            return modelMapper.map(accountDto, Gardener.class);
        }
        throw new IllegalArgumentException("Unknown account type");
    }

    public AccountDto mapToAccountDto(Account account) {
        if (account instanceof User) {
            return modelMapper.map(account, UserDto.class);
        }
        if (account instanceof Gardener) {
            return modelMapper.map(account, GardenerDto.class);
        }
        throw new IllegalArgumentException("Unknown account type");
    }
}
