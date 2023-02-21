package com.tinkoffacademy.landscape.service;

import com.google.protobuf.Empty;
import com.tinkoffacademy.landscape.dto.StatusDTO;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatusService {
    @GrpcClient("handyman-server")
    private StatusServiceGrpc.StatusServiceBlockingStub handymanStub;

    @GrpcClient("rancher-server")
    private StatusServiceGrpc.StatusServiceBlockingStub rancherStub;

    public Map<String, StatusDTO[]> getStatuses() {
        StatusDTO handymanStatus = getStatusDTO(handymanStub);
        StatusDTO rancherStatus = getStatusDTO(rancherStub);
        return Map.of(
                handymanStatus.getName(), new StatusDTO[]{handymanStatus},
                rancherStatus.getName(), new StatusDTO[]{rancherStatus}
        );
    }

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
