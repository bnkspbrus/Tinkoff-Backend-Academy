package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
