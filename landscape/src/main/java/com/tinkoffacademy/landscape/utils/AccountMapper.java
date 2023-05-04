package com.tinkoffacademy.landscape.utils;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;

/**
 * AccountMapper is a class that maps Account to AccountDto and vice versa.
 *
 * @see com.tinkoffacademy.landscape.dto.AccountDto
 * @see com.tinkoffacademy.landscape.model.Account
 */
@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper modelMapper;

    /**
     * Maps AccountDto to Account using ModelMapper.
     */
    public Account mapToAccount(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

    /**
     * Maps fields from AccountDto to Account using ModelMapper. ModelMapper skips id field of AccountDto.
     */
    public Account mapToAccount(AccountDto accountDto, Account account) {
        // configure ModelMapper to skip id field of AccountDto using PropertyMap
        modelMapper.typeMap(AccountDto.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        modelMapper.map(accountDto, account);
        return account;
    }

    public AccountCredProto mapToAccountCredProto(Account account) {
        return modelMapper.map(account, AccountCredProto.Builder.class).build();
    }

    public Account mapToAccount(AccountProto accountProto) {
        return modelMapper.map(accountProto, Account.class);
    }
}
