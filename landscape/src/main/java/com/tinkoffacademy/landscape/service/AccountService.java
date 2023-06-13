package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.dto.BankStat;
import com.tinkoffacademy.landscape.dto.GardenerStat;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.enums.AccountType;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.utils.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto getById(Long id) {
        Account account = getAccountById(id);
        return accountMapper.mapToAccountDto(account);
    }

    public AccountDto getByFieldId(Long id) {
        Account account = accountRepository.findByFieldId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
        return accountMapper.mapToAccountDto(account);
    }

    public AccountDto getByUserAccountId(Long id) {
        Account account = accountRepository.findByUserAccountId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
        return accountMapper.mapToAccountDto(account);
    }

    public List<AccountDto> findAll(AccountType type) {
        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::mapToAccountDto)
                .filter(accountDto -> type == null || accountDto.getType() == type)
                .toList();
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.mapToAccount(accountDto);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    @Transactional
    public AccountDto updateById(Long id, AccountDto accountDto) {
        Account account = getAccountById(id);
        if (accountDto.getType() != account.getType()) {
            throw new ResponseStatusException(
                    BAD_REQUEST,
                    "Account type can't be changed"
            );
        }
        accountDto.setId(id);
        account = accountMapper.mapToAccount(accountDto);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public List<AccountDto> findAllSortByLastName() {
        return accountRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(accountMapper::mapToAccountDto)
                .toList();
    }

    public List<GardenerStat> findStatGroupByLogin() {
        return accountRepository.findStatGroupByLogin();
    }

    public List<BankStat> findStatGroupByBank() {
        return accountRepository.findStatGroupByBank();
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
    }
}
