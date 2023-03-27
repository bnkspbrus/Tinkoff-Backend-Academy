package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceImplBase;
import ru.tinkoff.proto.UUIDProto;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class AccountServiceGrpcImpl extends AccountServiceImplBase {

    private final AccountTypeV2Service accountTypeV2Service;

    private final AccountRepository accountRepository;

    @Override
    public void save(AccountProto request, StreamObserver<UUIDProto> responseObserver) {
        AccountTypeV2 typeV2 = accountTypeV2Service.findByTypeName(request.getTypeName());
        Account account = Account.builder()
                .id(UUID.fromString(request.getId().getValue()))
                .typeV2(typeV2)
                .login(request.getLogin())
                .email(request.getEmail())
                .phone(request.getPhone())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
        account = accountRepository.save(account);
        UUIDProto uuid = UUIDProto.newBuilder()
                .setValue(account.getId().toString())
                .build();
        responseObserver.onNext(uuid);
        responseObserver.onCompleted();
    }
}
