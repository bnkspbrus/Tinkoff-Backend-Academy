package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.utils.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeV2Service accountTypeV2Service;
    private final AccountMapper accountMapper;

    public Account getById(Long id) {
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

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public Account updateById(Long id, AccountDto accountDto) {
        Account account = getById(id);
        account = accountMapper.mapToAccount(accountDto, account);
        account.setId(id);
        return accountRepository.save(account);
    }

    public List<Account> findAllSortByLastName() {
        return accountRepository.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
    }
}
