package com.tinkoffacademy.rancher.service;

import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class SystemService {
    private final BuildProperties buildProperties;

    private ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 8989)
            .usePlaintext()
            .build();

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
