package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import com.tinkoffacademy.handyman.utils.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
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
    private final AccountMapper accountMapper;
    @GrpcClient("landscape")
    @Setter
    private AccountServiceBlockingStub landscapeStub;

    public AccountDto getById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Handyman account with id " + id + " not found"));

        return accountMapper.mapToAccountDto(account);
    }

    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::mapToAccountDto)
                .peek(accountDto -> accountDto.setId(null))
                .collect(Collectors.toList());
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        AccountProto accountProto = accountMapper.mapToAccountProto(accountDto);
        UUIDProto uuid = landscapeStub.save(accountProto);
        Account account = accountMapper.mapToAccount(accountDto);
        account.setParentUUID(uuid.getValue());
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public AccountDto updateById(String id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Handyman account with id " + id + " not found"));
        account = accountMapper.mapToAccount(accountDto, account);
        account.setId(id);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }
}
