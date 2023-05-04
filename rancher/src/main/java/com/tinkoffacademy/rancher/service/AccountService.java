package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.rancher.dto.AccountDto;
import com.tinkoffacademy.rancher.document.Account;
import com.tinkoffacademy.rancher.repository.AccountRepository;
import com.tinkoffacademy.rancher.utils.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.IdProto;

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
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Rancher account with id " + id + " not found"));

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
        IdProto id = landscapeStub.save(accountProto);
        Account account = accountMapper.mapToAccount(accountDto);
        account.setParentId(id.getValue());
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public AccountDto updateById(String id, AccountDto accountDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Rancher account with id " + id + " not found"));
        account = accountMapper.mapToAccount(accountDto, account);
        account.setId(id);
        account = accountRepository.save(account);
        return accountMapper.mapToAccountDto(account);
    }

    public void deleteById(String id) {
        accountRepository.deleteById(id);
    }
}
