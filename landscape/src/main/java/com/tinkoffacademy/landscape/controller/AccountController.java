package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.dto.BankStat;
import com.tinkoffacademy.landscape.dto.GardenerStat;
import com.tinkoffacademy.landscape.enums.AccountType;
import com.tinkoffacademy.landscape.service.AccountService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get account by id")
    public AccountDto getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping("/field/{id}")
    @Timed(value = "getByFieldId.time", description = "Time taken to get account by field id")
    public AccountDto getByFieldId(@PathVariable Long id) {
        return accountService.getByFieldId(id);
    }

    @GetMapping("/user-account/{id}")
    @Timed(value = "getByUserAccountId.time", description = "Time taken to get account by user account id")
    public AccountDto getByUserAccountId(@PathVariable Long id) {
        return accountService.getByUserAccountId(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all accounts")
    public List<AccountDto> findAll(@RequestParam(required = false) AccountType type) {
        return accountService.findAll(type);
    }

    @GetMapping("/sort/lastname")
    public List<AccountDto> findAllSortByLastName() {
        return accountService.findAllSortByLastName();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save account")
    public AccountDto save(@RequestBody AccountDto accountDto) {
        return accountService.save(accountDto);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update account by id")
    public AccountDto updateById(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return accountService.updateById(id, accountDto);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete account by id")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }

    @GetMapping("/stat/gardener")
    @Timed(value = "findGardenerStat.time", description = "Time taken to find gardener stat")
    public List<GardenerStat> findGardenerStat() {
        return accountService.findStatGroupByLogin();
    }

    @GetMapping("/stat/bank")
    @Timed(value = "findBankStat.time", description = "Time taken to find bank stat")
    public List<BankStat> findBankStat() {
        return accountService.findStatGroupByBank();
    }
}
