package com.tinkoffacademy.handyman.repository;

import com.tinkoffacademy.handyman.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
