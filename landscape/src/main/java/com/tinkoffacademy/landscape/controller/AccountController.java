package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.service.AccountService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get account by id")
    public Account findById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all accounts")
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/sort/lastname")
    public List<Account> findAllSortByLastName() {
        return accountService.findAllSortByLastName();
    }
    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save account")
    public Account save(@RequestBody AccountDto accountDto) {
        return accountService.save(accountDto);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update account by id")
    public Account save(@PathVariable Long id, @RequestBody AccountDto accountDto) {
        return accountService.updateById(id, accountDto);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete account by id")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
