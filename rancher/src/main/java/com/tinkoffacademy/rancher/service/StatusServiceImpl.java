package com.tinkoffacademy.rancher.service;

import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.boot.info.BuildProperties;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

@GrpcService
@RequiredArgsConstructor
public class StatusServiceImpl extends StatusServiceGrpc.StatusServiceImplBase {
    private final BuildProperties buildProperties;

    @Override
    public void getVersion(Empty request, StreamObserver<VersionResponse> responseObserver) {
        responseObserver.onNext(getVersion());
        responseObserver.onCompleted();
    }

    @Override
    public void getReadiness(Empty request, StreamObserver<ReadinessResponse> responseObserver) {
        ReadinessResponse response = ReadinessResponse.newBuilder().setStatus("OK").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private VersionResponse getVersion() {
        return VersionResponse
                .newBuilder()
                .setArtifact(buildProperties.getArtifact())
                .setName(buildProperties.getName())
                .setGroup(buildProperties.getGroup())
                .setVersion(buildProperties.getVersion())
                .build();
    }
}
