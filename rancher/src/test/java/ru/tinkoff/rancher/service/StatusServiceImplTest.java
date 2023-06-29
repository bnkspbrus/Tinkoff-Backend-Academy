package ru.tinkoff.rancher.service;

import com.google.protobuf.Empty;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc;
import ru.tinkoff.proto.VersionResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = {
        "grpc.server.inProcessName=test", // Enable inProcess server
        "grpc.server.port=-1", // Disable external server
        "grpc.client.inProcess.address=in-process:test" // Configure the client to connect to the inProcess server
})
@DirtiesContext
class StatusServiceImplTest {
    @GrpcClient("inProcess")
    private StatusServiceGrpc.StatusServiceBlockingStub blockingStub;

    @Autowired
    private BuildProperties buildProperties;

    @Test
    @DirtiesContext
    public void testGetReadiness() {
        // Given blockingStub

        // When
        ReadinessResponse response = blockingStub.getReadiness(Empty.getDefaultInstance());

        // Then
        assertEquals("OK", response.getStatus());
    }

    @Test
    @DirtiesContext
    public void testGetVersion() {
        // Given blockingStub

        // When
        VersionResponse response = blockingStub.getVersion(Empty.getDefaultInstance());

        // Then
        assertEquals(buildProperties.getName(), response.getName());
        assertEquals(buildProperties.getGroup(), response.getGroup());
        assertEquals(buildProperties.getArtifact(), response.getArtifact());
        assertEquals(buildProperties.getVersion(), response.getVersion());
    }
}