package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.utils.AccountMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceImplBase;
import ru.tinkoff.proto.UUIDProto;

import java.time.LocalDateTime;
import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class AccountServiceGrpcImpl extends AccountServiceImplBase {
    private final AccountTypeV2Service accountTypeV2Service;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Override
    public void save(AccountProto request, StreamObserver<UUIDProto> responseObserver) {
        LocalDateTime now = LocalDateTime.now();
        AccountTypeV2 typeV2 = accountTypeV2Service.getByName(request.getTypeName());
        Account account = accountMapper.mapToAccount(request);
        account.setCreation(now);
        account.setUpdating(now);
        account.setTypeV2(typeV2);
        account = accountService.save(account);
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(account.getId().toString())
                .build();
        responseObserver.onNext(uuid);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(UUIDProto request, StreamObserver<AccountCredProto> responseObserver) {
        UUID uuid = UUID.fromString(request.getValue());
        Account account = accountService.getById(uuid);
        AccountCredProto accountCredProto = accountMapper.mapToAccountCredProto(account);
        responseObserver.onNext(accountCredProto);
        responseObserver.onCompleted();
    }
}
