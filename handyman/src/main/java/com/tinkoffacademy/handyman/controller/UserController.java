package com.tinkoffacademy.handyman.controller;

import com.tinkoffacademy.handyman.entity.User;
import com.tinkoffacademy.handyman.service.UserService;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get user by id")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save user")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update user by id")
    public User updateById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateById(id, user);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete user by id")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
