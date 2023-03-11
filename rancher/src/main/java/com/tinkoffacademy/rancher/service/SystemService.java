package com.tinkoffacademy.rancher.service;

import java.util.Map;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Setter;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
public class SystemService {

    private final BuildProperties buildProperties;

    @Setter
    private ManagedChannel channel;

    public SystemService(BuildProperties buildProperties, GrpcServerProperties serverProperties) {
        this.buildProperties = buildProperties;
        channel = ManagedChannelBuilder
                .forAddress(serverProperties.getAddress(), serverProperties.getPort())
                .usePlaintext()
                .build();
    }

    /**
     * Gets readiness state
     *
     * @return pair of server name and its state of connectivity
     */
    public Map<String, String> getReadiness() {
        ConnectivityState state = channel.getState(true);
        return Map.of(buildProperties.getName(), state.name());
    }
}
