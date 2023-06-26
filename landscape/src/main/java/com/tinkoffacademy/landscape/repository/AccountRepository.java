package com.tinkoffacademy.landscape.repository;

import com.tinkoffacademy.landscape.dto.BankStat;
import com.tinkoffacademy.landscape.dto.GardenerStat;
import com.tinkoffacademy.landscape.entity.Account;
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
    List<GardenerStat> getGardenerStat();

    @Query("select " +
            "new com.tinkoffacademy.landscape.dto.BankStat(ua.bank, min(a.creation), max(a.creation)) " +
            "from UserAccount ua join Account a on ua.user.id=a.id group by ua.bank")
    List<BankStat> getBankStat();

    @Query("select f.gardener from Field f where f.id=:fieldId")
    Optional<Account> findByFieldId(@Param("fieldId") Long fieldId);

    @Query("select a.user from UserAccount a where a.id=:accountId")
    Optional<Account> findByUserAccountId(@Param("accountId") Long accountId);

    @Query("select a from Account a where a.type.name = :type or :type is null")
    List<Account> findAllByType(@Param("type") String type);
}
