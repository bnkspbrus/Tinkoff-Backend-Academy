package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountTypeV2Service accountTypeV2Service;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account save(AccountDto accountDto) {
        AccountTypeV2 typeV2 = accountTypeV2Service.findByTypeName(accountDto.typeName());
        Account account = Account.builder()
                .id(accountDto.id())
                .latitude(accountDto.latitude())
                .longitude(accountDto.longitude())
                .creation(accountDto.creation())
                .updating(accountDto.updating())
                .login(accountDto.login())
                .email(accountDto.email())
                .phone(accountDto.phone())
                .typeV2(typeV2)
                .build();
        return accountRepository.save(account);
    }
}
