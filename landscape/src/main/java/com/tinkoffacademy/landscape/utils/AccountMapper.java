package com.tinkoffacademy.landscape.utils;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.entity.Account;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;

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
        return modelMapper.map(accountDto, Account.class);
    }

    /**
     * Maps fields from AccountDto to Account using ModelMapper. ModelMapper skips creation and updating fields.
     */
    public Account mapToAccount(AccountDto accountDto, Account account) {
        // configure ModelMapper to skip creation and updating fields using typeMap
        modelMapper.typeMap(AccountDto.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setCreation))
                .addMappings(mapper -> mapper.skip(Account::setUpdating));
        modelMapper.map(accountDto, account);
        return account;
    }

    public AccountCredProto mapToAccountCredProto(Account account) {
        AccountCredProto.Builder builder = AccountCredProto.newBuilder();
        modelMapper.map(account, builder);
        return builder.build();
    }

    public Account mapToAccount(AccountProto accountProto) {
        return modelMapper.map(accountProto, Account.class);
    }

    public AccountDto mapToAccountDto(Account account) {
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        accountDto.setTypeName(account.getTypeV2().getTypeName());
        return accountDto;
    }
}
