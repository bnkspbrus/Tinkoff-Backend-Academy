package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.UserAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserService userService;

    public UserAccountDto getUserAccountById(Long id) {
        return userService.getUserByUserAccountId(id)
                .getUserAccounts()
                .stream()
                .filter(userAccountDto -> userAccountDto.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User account not found"
                ));
    }

    public List<UserAccountDto> getAllUserAccounts() {
        return userService.getAllUsers()
                .stream()
                .flatMap(userDto -> userDto.getUserAccounts().stream())
                .toList();
    }

    public UserAccountDto saveUserAccountWithUserId(Long userId, UserAccountDto userAccountDto) {
        var userDto = userService.getUserById(userId);
        userDto.getUserAccounts().add(userAccountDto);
        var updated = userService.updateUser(userDto);
        return updated.getUserAccounts().get(updated.getUserAccounts().size() - 1);
    }

    public UserAccountDto updateUserAccount(UserAccountDto userAccountDto) {
        var userDto = userService.getUserByUserAccountId(userAccountDto.getId());
        var userAccountDtos = userDto.getUserAccounts();
        var indexes = userAccountDtos.stream().map(UserAccountDto::getId).toList();
        var index = indexes.indexOf(userAccountDto.getId());
        userAccountDtos.set(index, userAccountDto);
        var updated = userService.updateUser(userDto);
        return updated.getUserAccounts().get(index);
    }

    public void deleteUserAccount(Long id) {
        var userDto = userService.getUserByUserAccountId(id);
        var userAccountDtos = userDto.getUserAccounts();
        var indexes = userAccountDtos.stream().map(UserAccountDto::getId).toList();
        var index = indexes.indexOf(id);
        userAccountDtos.remove(index);
        userService.updateUser(userDto);
    }
}
