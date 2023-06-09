package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.entity.UserAccount;
import com.tinkoffacademy.handyman.service.UserAccountService;
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
@RequestMapping("/users/accounts")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService accountService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get account by id")
    public UserAccount findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all accounts")
    public List<UserAccount> findAll() {
        return accountService.findAll();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save account")
    public UserAccount save(@RequestBody UserAccount userAccount) {
        return accountService.save(userAccount);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update account by id")
    public UserAccount updateById(@PathVariable Long id, @RequestBody UserAccount userAccount) {
        return accountService.updateById(id, userAccount);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete account by id")
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
