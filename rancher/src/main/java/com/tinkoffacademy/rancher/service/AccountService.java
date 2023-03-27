package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.rancher.dto.AccountDto;
import com.tinkoffacademy.rancher.model.Account;
import com.tinkoffacademy.rancher.repository.AccountRepository;
import com.tinkoffacademy.rancher.view.AccountExt;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
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

    public Account save(AccountDto accountDto) {
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(accountDto.id())
                .build();
        AccountProto accountProto = AccountProto.newBuilder()
                .setId(uuid)
                .setTypeName(accountDto.typeName())
                .setLogin(accountDto.login())
                .setEmail(accountDto.email())
                .setPhone(accountDto.phone())
                .setLatitude(accountDto.latitude())
                .setLongitude(accountDto.longitude())
                .build();
        uuid = landscapeStub.save(accountProto);
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
