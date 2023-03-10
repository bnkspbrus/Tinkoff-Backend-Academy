package com.tinkoffacademy.handyman.service;

import java.util.Map.Entry;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Setter;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

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
     * @return pair of server name and state of connectivity
     */
    public Entry<String, String> getReadiness() {
        ConnectivityState state = channel.getState(true);
        return entry(buildProperties.getName(), state.name());
    }
}
