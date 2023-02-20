package com.tinkoffacademy.handyman.service;

import com.tinkofflibrary.lib.Status;
import com.tinkofflibrary.lib.HandymanStatuses;
import com.tinkofflibrary.lib.StatusServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;

@GrpcService
@RequiredArgsConstructor
public class HandymanStatusService extends StatusServiceGrpc.StatusServiceImplBase {
    private final BuildProperties buildProperties;

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void getStatus(HandymanStatuses request, StreamObserver<HandymanStatuses> responseObserver) {
        HandymanStatuses response = HandymanStatuses
                .newBuilder()
                .addAllHandymanService(request.getHandymanServiceList())
                .addHandymanService(getStatus())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private Status getStatus() {
        return Status
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
