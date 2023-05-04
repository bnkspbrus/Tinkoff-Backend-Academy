package com.tinkoffacademy.rancher.repository;

import com.tinkoffacademy.rancher.document.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
