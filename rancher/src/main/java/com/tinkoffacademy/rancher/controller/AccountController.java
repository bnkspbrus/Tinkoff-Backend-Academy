package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.rancher.dto.AccountDto;
import com.tinkoffacademy.rancher.service.AccountService;
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
    public AccountDto getById(@PathVariable String id) {
        return accountService.getById(id);
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
    public AccountDto updateById(@PathVariable String id, @RequestBody AccountDto accountDto) {
        return accountService.updateById(id, accountDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        accountService.deleteById(id);
    }
}
