package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.dto.BankStat;
import com.tinkoffacademy.landscape.dto.GardenerStat;
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

    public AccountDto getAccountDtoById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
        return accountMapper.mapToAccountDto(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Account with id " + id + " not found"
                ));
    }

    public List<AccountDto> findAll() {
        return accountRepository
                .findAll()
                .stream()
                .map(accountMapper::mapToAccountDto)
                .toList();
    }

    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.mapToAccount(accountDto);
        LocalDateTime now = LocalDateTime.now();
        account.setCreation(now);
        account.setUpdating(now);
        AccountTypeV2 typeV2 = accountTypeV2Service.getByName(accountDto.getTypeName());
        account.setTypeV2(typeV2);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountDto updateById(Long id, AccountDto accountDto) {
        Account account = getAccountById(id);
        account = accountMapper.mapToAccount(accountDto, account);
        account.setId(id);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public List<AccountDto> findAllSortByLastName() {
        return accountRepository
                .findAll(Sort.by(Sort.Direction.ASC, "lastName"))
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
}
