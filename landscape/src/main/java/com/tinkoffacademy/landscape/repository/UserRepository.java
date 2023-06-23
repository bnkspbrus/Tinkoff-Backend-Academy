package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     *  Select users whose orders is empty
     *
     *  @return List of users
     */
    List<User> findByOrdersIsEmpty();
}
