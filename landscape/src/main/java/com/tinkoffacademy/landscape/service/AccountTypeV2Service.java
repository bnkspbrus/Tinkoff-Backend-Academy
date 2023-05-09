package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountTypeV2Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountTypeV2Service {
    private final AccountTypeV2Repository accountTypeV2Repository;

    public AccountTypeV2 getByName(String typeName) {
        return accountTypeV2Repository
                .findByTypeName(typeName)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                NOT_FOUND,
                                "AccountTypeV2 with typeName " + typeName + "not found"
                        )
                );
    }

    public AccountTypeV2 getAccountTypeV2ById(Long id) {
        return accountTypeV2Repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "AccountTypeV2 with id " + id + " not found"
                ));
    }

    public List<AccountTypeV2> findAll() {
        return accountTypeV2Repository.findAll();
    }

    public AccountTypeV2 save(AccountTypeV2 accountTypeV2) {
        return accountTypeV2Repository.save(accountTypeV2);
    }

    public AccountTypeV2 updateById(Long id, AccountTypeV2 accountTypeV2) {
        AccountTypeV2 accountTypeV2FromDb = getAccountTypeV2ById(id);
        accountTypeV2FromDb.setTypeName(accountTypeV2.getTypeName());
        return accountTypeV2Repository.save(accountTypeV2FromDb);
    }

    public void deleteById(Long id) {
        accountTypeV2Repository.deleteById(id);
    }
}
