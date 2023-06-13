package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.entity.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {
}
