package com.tinkoffacademy.handyman.utils;

import com.tinkoffacademy.handyman.dto.AccountDto;
import com.tinkoffacademy.handyman.model.Account;
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
public record AccountMapper(
        ModelMapper modelMapper,
        @GrpcClient("landscape") AccountServiceBlockingStub landscapeStub
) {
    /**
     * Maps AccountDto to Account using ModelMapper
     */
    public Account mapToAccount(AccountDto accountDto) {
        return modelMapper.map(accountDto, Account.class);
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
     * Maps AccountDto to AccountProto using AccountProto#newBuilder()
     * <p>
     * Maps all fields from AccountDto to AccountProto. Also sets typeName to "handyman".
     * </p>
     */
    public AccountProto mapToAccountProto(AccountDto accountDto) {
        return AccountProto.newBuilder()
                .setTypeName("handyman")
                .setLogin(accountDto.login())
                .setEmail(accountDto.email())
                .setPhone(accountDto.phone())
                .setLatitude(accountDto.latitude())
                .setLongitude(accountDto.longitude())
                .build();
    }
}
