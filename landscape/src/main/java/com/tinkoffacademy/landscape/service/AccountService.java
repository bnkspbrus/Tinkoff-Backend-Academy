package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountTypeV2Service accountTypeV2Service;

    public Account findById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Account with id " + id + " not found"));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account save(AccountDto accountDto) {
        LocalDateTime now = LocalDateTime.now();
        AccountTypeV2 typeV2 = accountTypeV2Service.findByTypeName(accountDto.typeName());
        Account account = Account.builder()
                .id(accountDto.id())
                .latitude(accountDto.latitude())
                .longitude(accountDto.longitude())
                .login(accountDto.login())
                .email(accountDto.email())
                .phone(accountDto.phone())
                .typeV2(typeV2)
                .creation(now)
                .updating(now)
                .build();
        return accountRepository.save(account);
    }

    public void deleteById(UUID id) {
        accountRepository.deleteById(id);
    }
}
