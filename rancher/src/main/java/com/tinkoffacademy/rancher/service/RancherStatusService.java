package com.tinkoffacademy.handyman.service;

import com.tinkofflibrary.lib.ServerStatus;
import com.tinkofflibrary.lib.ServerStatuses;
import com.tinkofflibrary.lib.StatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;

@GrpcService
@RequiredArgsConstructor
public class RancherStatusService extends StatusServiceGrpc.StatusServiceImplBase {
    private final BuildProperties buildProperties;

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void getStatus(ServerStatuses request, StreamObserver<ServerStatuses> responseObserver) {
        ServerStatuses response = ServerStatuses
                .newBuilder()
                .addAllRancherService(request.getRancherServiceList())
                .addRancherService(getServerStatus())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ServerStatus getServerStatus() {
        return ServerStatus
                .newBuilder()
                .setHost("localhost:" + serverPort)
                .setStatus("OK")
                .setArtifact(buildProperties.getArtifact())
                .setName(buildProperties.getName())
                .setGroup(buildProperties.getGroup())
                .setVersion(buildProperties.getVersion())
                .build();
    }
}
