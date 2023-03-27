package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public Account findById(@PathVariable String id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @PostMapping
    public Account save(@RequestBody AccountDto accountDto) {
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
