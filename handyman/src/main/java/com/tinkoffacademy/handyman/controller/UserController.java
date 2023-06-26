package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.dto.UserDto;
import com.tinkoffacademy.handyman.service.UserService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.Time;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to return a user")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to return all users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save a user")
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping
    @Timed(value = "updateById.time", description = "Time taken to update a user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUser(userDto);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete a user")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
