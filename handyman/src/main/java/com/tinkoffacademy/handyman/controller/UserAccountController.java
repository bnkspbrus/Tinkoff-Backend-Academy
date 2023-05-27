package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.entity.UserAccount;
import com.tinkoffacademy.handyman.service.UserAccountService;
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
@RequestMapping("/users/accounts")
public record UserAccountController(
        UserAccountService accountService
) {
    @GetMapping("/{id}")
    public UserAccount findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<UserAccount> findAll() {
        return accountService.findAll();
    }

    @PostMapping
    public UserAccount save(@RequestBody UserAccount userAccount) {
        return accountService.save(userAccount);
    }

    @PutMapping("/{id}")
    public UserAccount updateById(@PathVariable Long id, @RequestBody UserAccount userAccount) {
        return accountService.updateById(id, userAccount);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
