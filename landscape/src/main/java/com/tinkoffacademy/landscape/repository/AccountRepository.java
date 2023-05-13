package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.dto.GardenerStat;
import com.tinkoffacademy.landscape.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select " +
            "new com.tinkoffacademy.landscape.dto.GardenerStat(a.login, min(ST_Area(f.area)), max(ST_Area(f.area)), avg(ST_Area(f.area))) " +
            "from Field f join Account a on f.gardener.id=a.id group by a.login")
    List<GardenerStat> findStatGroupByLogin();
}
