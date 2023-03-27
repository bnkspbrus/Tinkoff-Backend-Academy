package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.rancher.dto.AccountDto;
import com.tinkoffacademy.rancher.model.Account;
import com.tinkoffacademy.rancher.service.AccountService;
import com.tinkoffacademy.rancher.view.AccountExt;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountExt findById(@PathVariable String id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<AccountExt> findAll() {
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
