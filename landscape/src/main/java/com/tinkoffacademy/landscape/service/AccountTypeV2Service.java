package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountTypeV2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountTypeV2Service {
    private final AccountTypeV2Repository accountTypeV2Repository;

    public AccountTypeV2 getByName(String typeName) {
        return accountTypeV2Repository.findByTypeName(typeName)
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND, "AccountTypeV2 with typeName " + typeName + "not found"
                        )
                );
    }
}
