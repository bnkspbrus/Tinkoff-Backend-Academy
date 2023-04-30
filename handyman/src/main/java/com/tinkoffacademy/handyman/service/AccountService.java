package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import com.tinkoffacademy.handyman.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
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
public class AccountService {
    private final AccountRepository accountRepository;

    private final AccountServiceBlockingStub landscapeStub;

    public AccountService(AccountRepository accountRepository, @GrpcClient("landscape") AccountServiceBlockingStub landscapeStub) {
        this.accountRepository = accountRepository;
        this.landscapeStub = landscapeStub;
    }

    public AccountDto findById(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Handyman account with id " + id + " not found"));

        return toAccountDto(account);
    }

    public List<AccountDto> findAll() {
        return accountRepository.findAll().stream()
                .map(this::toAccountDto)
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

    /**
     * Converts Account to AccountDto
     *
     * @param account Account
     * @return AccountDto
     */
    private AccountDto toAccountDto(Account account) {
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(account.getParentUUID())
                .build();
        AccountCredProto accountCredProto = landscapeStub.findById(uuid);
        // map account and accountCredProto to AccountDto
        // hint: use id, skills, latitude, longitude from account
        // hint: use login, email, phone from accountCredProto
        return new AccountDto(
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
