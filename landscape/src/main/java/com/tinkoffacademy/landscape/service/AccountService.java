package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.dto.AccountDto;
import com.tinkoffacademy.dto.BankStat;
import com.tinkoffacademy.dto.GardenerStat;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import com.tinkoffacademy.landscape.utils.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    public AccountDto getById(Long id) {
        Account account = getAccountById(id);
        return accountMapper.mapToAccountDto(account);
    }

    @Transactional(readOnly = true)
    public AccountDto getByFieldId(Long id) {
        Account account = accountRepository.findByFieldId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
        return accountMapper.mapToAccountDto(account);
    }

    @Transactional(readOnly = true)
    public AccountDto getByUserAccountId(Long id) {
        Account account = accountRepository.findByUserAccountId(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
        return accountMapper.mapToAccountDto(account);
    }

    @Transactional(readOnly = true)
    public List<AccountDto> findAll(String type) {
        return accountRepository
                .findAllByType(type)
                .stream()
                .map(accountMapper::mapToAccountDto)
                .toList();
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        accountDto.setId(null);
        Account account = accountMapper.mapToAccount(accountDto);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    @Transactional
    public AccountDto updateById(Long id, AccountDto accountDto) {
        Account account = getAccountById(id);
        if (!Objects.equals(accountDto.getType(), account.getType().getName())) {
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

    @Transactional
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<AccountDto> findAllSortByLastName() {
        return accountRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(accountMapper::mapToAccountDto)
                .toList();
    }

    public List<GardenerStat> getGardenerStat() {
        return accountRepository.getGardenerStat();
    }

    public List<BankStat> getBankStat() {
        return accountRepository.getBankStat();
    }

    private Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
    }
}
