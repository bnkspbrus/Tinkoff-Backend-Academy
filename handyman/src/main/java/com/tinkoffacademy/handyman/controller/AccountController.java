package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.service.AccountService;
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
public record AccountController(
        AccountService accountService
) {

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable String id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<AccountDto> findAll() {
        return accountService.findAll();
    }

    @PostMapping
    public AccountDto save(@RequestBody AccountDto accountDto) {
        return accountService.save(accountDto);
    }

    @PutMapping("/{id}")
    public Account updateById(@PathVariable String id, @RequestBody Account account) {
        return accountService.updateById(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        accountService.deleteById(id);
    }
}
