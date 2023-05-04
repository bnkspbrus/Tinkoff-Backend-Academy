package com.tinkoffacademy.handyman.service;

import java.util.List;

import com.tinkoffacademy.handyman.entity.UserAccount;
import com.tinkoffacademy.handyman.repository.EntityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final EntityAccountRepository accountRepository;

    public UserAccount findById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Account with id " + id + " not " +
                        "found"));
    }

    public List<UserAccount> findAll() {
        return accountRepository.findAll();
    }

    public UserAccount save(UserAccount userAccount) {
        return accountRepository.save(userAccount);
    }

    public UserAccount updateById(Long id, UserAccount userAccount) {
        userAccount.setId(id);
        return accountRepository.save(userAccount);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
