package com.tinkoffacademy.handyman.utils;

import com.tinkoffacademy.handyman.dto.AccountDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import com.tinkoffacademy.handyman.document.Account;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceBlockingStub;
import ru.tinkoff.proto.IdProto;

/**
 * AccountMapper is a class that maps Account to AccountDto and vice versa.
 *
 * @see com.tinkoffacademy.handyman.dto.AccountDto
 * @see com.tinkoffacademy.handyman.document.Account
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
        modelMapper.map(accountDto, account);
        return account;
    }

    /**
     * Maps Account to AccountDto using AccountServiceBlockingStub to find AccountCredProto by Account's parentUUID
     */
    public AccountDto mapToAccountDto(Account account) {
        IdProto id = IdProto.newBuilder()
                .setValue(account.getParentId())
                .build();
        AccountCredProto accountCredProto = landscapeStub.findById(id);
        // return AccountDto with id, login, email, phone from AccountCredProto and latitude, longitude, skills from Account
        AccountDto accountDto = modelMapper.map(account, AccountDto.class);
        modelMapper.map(accountCredProto, accountDto);
        return accountDto;
    }

    /**
     * Maps AccountDto to AccountProto using ModelMapper. Also sets typeName to "handyman".
     */
    public AccountProto mapToAccountProto(AccountDto accountDto) {
        AccountProto.Builder builder = AccountProto.newBuilder();
        modelMapper.map(accountDto, builder);
        return builder
                .setTypeName("handyman")
                .build();
    }
}
