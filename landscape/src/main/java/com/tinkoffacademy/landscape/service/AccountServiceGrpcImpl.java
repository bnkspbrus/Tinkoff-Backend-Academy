package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.model.Account;
import com.tinkoffacademy.landscape.model.AccountTypeV2;
import com.tinkoffacademy.landscape.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.tinkoff.proto.AccountCredProto;
import ru.tinkoff.proto.AccountProto;
import ru.tinkoff.proto.AccountServiceGrpc.AccountServiceImplBase;
import ru.tinkoff.proto.UUIDProto;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

@GrpcService
@RequiredArgsConstructor
public class AccountServiceGrpcImpl extends AccountServiceImplBase {

    private final AccountTypeV2Service accountTypeV2Service;

    private final AccountRepository accountRepository;

    private final AccountService accountService;

    @Override
    public void save(AccountProto request, StreamObserver<UUIDProto> responseObserver) {
        AccountTypeV2 typeV2 = accountTypeV2Service.findByTypeName(request.getTypeName());
        LocalDateTime creation = LocalDateTime.ofEpochSecond(
                request.getCreation().getSeconds(),
                request.getCreation().getNanos(),
                UTC
        );
        LocalDateTime updating = LocalDateTime.ofEpochSecond(
                request.getUpdating().getSeconds(),
                request.getUpdating().getNanos(),
                UTC
        );
        Account account = Account.builder()
                .typeV2(typeV2)
                .login(request.getLogin())
                .email(request.getEmail())
                .phone(request.getPhone())
                .creation(creation)
                .updating(updating)
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

    @Override
    public void findById(UUIDProto request, StreamObserver<AccountCredProto> responseObserver) {
        UUID uuid = UUID.fromString(request.getValue());
        Account account = accountService.findById(uuid);
        AccountCredProto accountCredProto = AccountCredProto.newBuilder()
                .setLogin(account.getLogin())
                .setEmail(account.getEmail())
                .setPhone(account.getPhone())
                .build();
        responseObserver.onNext(accountCredProto);
        responseObserver.onCompleted();
    }
}
