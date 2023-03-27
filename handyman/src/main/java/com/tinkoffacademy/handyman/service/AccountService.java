package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.model.Account.AccountBuilder;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.UUIDProto;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @GrpcClient("landscape")
    private AccountServiceBlockingStub landscapeStub;

    public Account findById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Handyman account with id " + id + " not found"));
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
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
}
