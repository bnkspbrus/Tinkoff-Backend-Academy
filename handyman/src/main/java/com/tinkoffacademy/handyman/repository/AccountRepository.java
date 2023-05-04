package com.tinkoffacademy.handyman.repository;

import com.tinkoffacademy.handyman.document.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
