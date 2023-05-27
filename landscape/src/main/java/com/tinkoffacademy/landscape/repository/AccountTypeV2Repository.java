package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountTypeV2Repository extends JpaRepository<AccountTypeV2, Integer> {
    Optional<AccountTypeV2> findByTypeName(String name);
}
