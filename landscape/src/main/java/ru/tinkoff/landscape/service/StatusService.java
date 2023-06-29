package ru.tinkoff.landscape.service;

import com.google.protobuf.Empty;
import ru.tinkoff.landscape.status.ServerStatus;
import ru.tinkoff.landscape.status.StatusCollector;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

@Service
@RequiredArgsConstructor
@Setter
public class StatusService {
    private final Logger logger = LoggerFactory.getLogger(StatusService.class);
    @GrpcClient("handyman")
    private StatusServiceGrpc.StatusServiceBlockingStub handymanStub;
    @GrpcClient("rancher")
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
        logger.info("Receiving readiness response [{}] from the server", readiness);
        VersionResponse version = blockingStub.getVersion(Empty.getDefaultInstance());
        logger.info("Receiving version response [{}] from the server", version);
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
