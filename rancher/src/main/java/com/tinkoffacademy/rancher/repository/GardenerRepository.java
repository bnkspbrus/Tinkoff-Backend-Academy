package com.tinkoffacademy.rancher.repository;

import com.tinkoffacademy.rancher.entity.Gardener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenerRepository extends JpaRepository<Gardener, Long> {
}
