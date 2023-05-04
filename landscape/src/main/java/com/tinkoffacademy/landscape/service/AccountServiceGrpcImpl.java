package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.entity.Account;
import com.tinkoffacademy.landscape.entity.AccountTypeV2;
import com.tinkoffacademy.landscape.utils.AccountMapper;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceImplBase;
import ru.tinkoff.proto.IdProto;

import java.time.LocalDateTime;

@GrpcService
@RequiredArgsConstructor
public class AccountServiceGrpcImpl extends AccountServiceImplBase {
    private final AccountTypeV2Service accountTypeV2Service;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @Override
    public void save(AccountProto request, StreamObserver<IdProto> responseObserver) {
        LocalDateTime now = LocalDateTime.now();
        AccountTypeV2 typeV2 = accountTypeV2Service.getByName(request.getTypeName());
        Account account = accountMapper.mapToAccount(request);
        account.setCreation(now);
        account.setUpdating(now);
        account.setTypeV2(typeV2);
        account = accountService.save(account);
        IdProto id = IdProto.newBuilder()
                .setValue(account.getId())
                .build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    @Override
    public void findById(IdProto request, StreamObserver<AccountCredProto> responseObserver) {
        Account account = accountService.getById(request.getValue());
        AccountCredProto accountCredProto = accountMapper.mapToAccountCredProto(account);
        responseObserver.onNext(accountCredProto);
        responseObserver.onCompleted();
    }
}
