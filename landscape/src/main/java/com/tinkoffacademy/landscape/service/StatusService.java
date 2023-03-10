package com.tinkoffacademy.landscape.service;

import com.google.protobuf.Empty;
import com.tinkoffacademy.landscape.status.ServerStatus;
import com.tinkoffacademy.landscape.status.StatusCollector;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class StatusService {
    @GrpcClient("handyman-server")
    private StatusServiceGrpc.StatusServiceBlockingStub handymanStub;

    @GrpcClient("rancher-server")
    private StatusServiceGrpc.StatusServiceBlockingStub rancherStub;

    /**
     * Gets status of each server
     *
     * @return all handyman statuses and all rancher statuses
     */
    public StatusCollector getStatuses() {
        ServerStatus handymanStatus = mapToStatus(handymanStub);
        ServerStatus rancherStatus = mapToStatus(rancherStub);
        StatusCollector collector = new StatusCollector();
        collector.addHandyman(handymanStatus);
        collector.addRancher(rancherStatus);
        return collector;
    }

    /**
     * Gets status of server with given blocking stub
     *
     * @param blockingStub stub of server
     * @return status of server
     */
    private ServerStatus mapToStatus(StatusServiceGrpc.StatusServiceBlockingStub blockingStub) {
        ReadinessResponse readiness = blockingStub.getReadiness(Empty.getDefaultInstance());
        VersionResponse version = blockingStub.getVersion(Empty.getDefaultInstance());
        return ServerStatus
                .builder()
                .status(readiness.getStatus())
                .host(blockingStub.getChannel().authority())
                .name(version.getName())
                .group(version.getGroup())
                .artifact(version.getArtifact())
                .version(version.getVersion())
                .build();
    }
}
