package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserIsNullOrderByCreation();
}
