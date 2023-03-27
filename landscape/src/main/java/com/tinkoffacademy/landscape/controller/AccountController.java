package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @PostMapping
    public Account save(@RequestBody AccountDto accountDto) {
        return accountService.save(accountDto);
    }
}
