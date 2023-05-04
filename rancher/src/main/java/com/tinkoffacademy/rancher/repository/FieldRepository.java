package com.tinkoffacademy.rancher.repository;

import com.tinkoffacademy.rancher.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
