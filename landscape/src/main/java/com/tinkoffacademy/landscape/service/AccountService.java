package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.utils.AccountMapper;
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
    private final AccountMapper accountMapper;

    public Account getById(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Account with id " + id + " not found"));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account save(AccountDto accountDto) {
        Account account = accountMapper.mapToAccount(accountDto);
        LocalDateTime now = LocalDateTime.now();
        account.setCreation(now);
        account.setUpdating(now);
        AccountTypeV2 typeV2 = accountTypeV2Service.getByName(accountDto.getTypeName());
        account.setTypeV2(typeV2);
        return accountRepository.save(account);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(UUID id) {
        accountRepository.deleteById(id);
    }

    public Account updateById(UUID id, AccountDto accountDto) {
        Account account = getById(id);
        account = accountMapper.mapToAccount(accountDto, account);
        return accountRepository.save(account);
    }
}
