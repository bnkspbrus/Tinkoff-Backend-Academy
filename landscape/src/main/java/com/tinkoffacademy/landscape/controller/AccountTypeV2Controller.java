package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import com.tinkoffacademy.landscape.service.AccountTypeV2Service;
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
@RequestMapping("/account-types")
public record AccountTypeV2Controller(
        AccountTypeV2Service accountTypeV2Service
) {
    @GetMapping("/{id}")
    public AccountTypeV2 findById(@PathVariable("id") Long id) {
        return accountTypeV2Service.getAccountTypeV2ById(id);
    }

    @GetMapping
    public List<AccountTypeV2> findAll() {
        return accountTypeV2Service.findAll();
    }

    @PostMapping
    public AccountTypeV2 save(@RequestBody AccountTypeV2 accountTypeV2) {
        return accountTypeV2Service.save(accountTypeV2);
    }

    @PutMapping("/{id}")
    public AccountTypeV2 updateById(@PathVariable("id") Long id, @RequestBody AccountTypeV2 accountTypeV2) {
        return accountTypeV2Service.updateById(id, accountTypeV2);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        accountTypeV2Service.deleteById(id);
    }
}
