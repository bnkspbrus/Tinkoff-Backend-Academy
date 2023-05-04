package com.tinkoffacademy.handyman.utils;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.UUIDProto;

/**
 * AccountMapper is a class that maps Account to AccountDto and vice versa.
 *
 * @see com.tinkoffacademy.handyman.dto.AccountDto
 * @see com.tinkoffacademy.handyman.model.Account
 */
@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final ModelMapper modelMapper;
    @GrpcClient("landscape")
    @Setter
    private AccountServiceBlockingStub landscapeStub;

    /**
     * Maps AccountDto to Account using ModelMapper.
     */
    public Account mapToAccount(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
    }

    /**
     * Maps fields from AccountDto to Account using ModelMapper. ModelMapper skips id field of AccountDto.
     */
    public Account mapToAccount(AccountDto accountDto, Account account) {
        // configure ModelMapper to skip id field of AccountDto using PropertyMap
        modelMapper.typeMap(AccountDto.class, Account.class)
                .addMappings(mapper -> mapper.skip(Account::setId));
        modelMapper.map(accountDto, account);
        return account;
    }

    /**
     * Maps Account to AccountDto using AccountServiceBlockingStub to find AccountCredProto by Account's parentUUID
     */
    public AccountDto mapToAccountDto(Account account, boolean includeId) {
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(account.getParentUUID())
                .build();
        AccountCredProto accountCredProto = landscapeStub.findById(uuid);
        // return AccountDto with id, login, email, phone from AccountCredProto and latitude, longitude, skills from Account
        return new AccountDto(
                includeId ? account.getId() : null,
                accountCredProto.getLogin(),
                accountCredProto.getEmail(),
                accountCredProto.getPhone(),
                account.getLatitude(),
                account.getLongitude(),
                account.getSkills()
        );
    }

    /**
     * Maps AccountDto to AccountProto using ModelMapper. Also sets typeName to "handyman".
     */
    public AccountProto mapToAccountProto(AccountDto accountDto) {
        AccountProto accountProto = modelMapper.map(accountDto, AccountProto.Builder.class).build();
        return accountProto.toBuilder()
                .setTypeName("handyman")
                .build();
    }
}
