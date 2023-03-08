package com.tinkoffacademy.landscape.service;

import com.google.protobuf.Empty;
import com.tinkoffacademy.landscape.dto.StatusDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class StatusService {
    @GrpcClient("handyman")
    private StatusServiceGrpc.StatusServiceBlockingStub handymanStub;

    @GrpcClient("rancher")
    private StatusServiceGrpc.StatusServiceBlockingStub rancherStub;

    /**
     * Gets status for each server
     *
     * @return Map where key is server name and value is statuses of such server instances
     */
    public Map<String, StatusDTO[]> getStatuses() {
        StatusDTO handymanStatus = getStatusDTO(handymanStub);
        StatusDTO rancherStatus = getStatusDTO(rancherStub);
        return Map.of(
                "HandymanService", new StatusDTO[]{handymanStatus},
                "RancherService", new StatusDTO[]{rancherStatus}
        );
    }

    /**
     * Gets status of server with given blocking stub
     *
     * @param blockingStub stub of server
     * @return status of server as dto
     */
    private StatusDTO getStatusDTO(StatusServiceGrpc.StatusServiceBlockingStub blockingStub) {
        ReadinessResponse readiness = blockingStub.getReadiness(Empty.getDefaultInstance());
        VersionResponse version = blockingStub.getVersion(Empty.getDefaultInstance());
        return StatusDTO
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
