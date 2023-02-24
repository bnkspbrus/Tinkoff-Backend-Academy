package com.tinkoffacademy.handyman.service;

import com.google.protobuf.Empty;
import io.grpc.CallOptions;
import io.grpc.ConnectivityState;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;
import ru.tinkoff.proto.StatusServiceGrpc;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class SystemService {

    private ManagedChannel channel = ManagedChannelBuilder
            .forAddress("localhost", 9898)
            .usePlaintext()
            .build();
    private final BuildProperties buildProperties;

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
