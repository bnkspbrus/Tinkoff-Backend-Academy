package ru.tinkoff.handyman.controller;

import ru.tinkoff.handyman.dto.UserAccountDto;
import ru.tinkoff.handyman.service.UserAccountService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-accounts")
@RequiredArgsConstructor
public class UserAccountController {
    private final UserAccountService userAccountService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to return a user account")
    public UserAccountDto getUserAccountById(@PathVariable Long id) {
        return userAccountService.getUserAccountById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to return all user accounts")
    public List<UserAccountDto> getAllUserAccounts() {
        return userAccountService.getAllUserAccounts();
    }

    @PostMapping("/user/{userId}")
    @Timed(value = "save.time", description = "Time taken to save a user account with user id")
    public UserAccountDto saveUserAccountWithUserId(@PathVariable Long userId, @RequestBody UserAccountDto userAccountDto) {
        return userAccountService.saveUserAccountWithUserId(userId, userAccountDto);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update a user account")
    public UserAccountDto updateUserAccount(@RequestBody UserAccountDto userAccountDto) {
        return userAccountService.updateUserAccount(userAccountDto);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete a user account")
    public void deleteUserAccount(@PathVariable Long id) {
        userAccountService.deleteUserAccount(id);
    }
}
