package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.dto.AccountDto;
import com.tinkoffacademy.landscape.dto.BankStat;
import com.tinkoffacademy.landscape.dto.GardenerStat;
import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select " +
            "new com.tinkoffacademy.landscape.dto.GardenerStat(a.login, min(ST_Area(f.area)), max(ST_Area(f.area)), avg(ST_Area(f.area))) " +
            "from Field f join Account a on f.gardener.id=a.id group by a.login")
    List<GardenerStat> findStatGroupByLogin();

    @Query("select " +
            "new com.tinkoffacademy.landscape.dto.BankStat(ua.bank, min(u.creation), max(u.creation)) " +
            "from UserAccount ua join User u on ua.user.id=u.id group by ua.bank")
    List<BankStat> findStatGroupByBank();

    @Query("select gardener from Field where id=:fieldId")
    Optional<Account> findByFieldId(@Param("fieldId") Long fieldId);

    @Query("select user from UserAccount where id=:accountId")
    Optional<Account> findByUserAccountId(@Param("accountId") Long accountId);
}
