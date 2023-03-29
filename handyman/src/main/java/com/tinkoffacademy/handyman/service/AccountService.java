package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import com.tinkoffacademy.handyman.view.AccountExt;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.UUIDProto;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @GrpcClient("landscape")
    private AccountServiceBlockingStub landscapeStub;

    public AccountExt findById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Handyman account with id " + id + " not found"));

        return toAccountExt(account);
    }

    public List<AccountExt> findAll() {
        return accountRepository.findAll().stream()
                .map(this::toAccountExt)
                .collect(Collectors.toList());
    }

    @Transactional
    public Account save(AccountDto accountDto) {
        AccountProto accountProto = AccountProto.newBuilder()
                .setTypeName("handyman")
                .setLogin(accountDto.login())
                .setEmail(accountDto.email())
                .setPhone(accountDto.phone())
                .setLatitude(accountDto.latitude())
                .setLongitude(accountDto.longitude())
                .build();
        UUIDProto uuid = landscapeStub.save(accountProto);
        Account account = Account.builder()
                .latitude(accountDto.latitude())
                .longitude(accountDto.longitude())
                .skills(accountDto.skills())
                .parentUUID(uuid.getValue())
                .build();
        return accountRepository.save(account);
    }

    public Account updateById(String id, Account account) {
        account.setId(id);
        return accountRepository.save(account);
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }

    private AccountExt toAccountExt(Account account) {
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(account.getParentUUID())
                .build();
        AccountCredProto accountCredProto = landscapeStub.findById(uuid);
        return new AccountExt(
                account.getId(),
                accountCredProto.getLogin(),
                accountCredProto.getEmail(),
                accountCredProto.getPhone(),
                account.getLatitude(),
                account.getLongitude(),
                account.getSkills()
        );
    }
}
