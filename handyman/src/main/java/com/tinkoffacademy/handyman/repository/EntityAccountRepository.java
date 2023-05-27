package com.tinkoffacademy.handyman.repository;

import com.tinkoffacademy.handyman.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityAccountRepository extends JpaRepository<UserAccount, Long> {
}
