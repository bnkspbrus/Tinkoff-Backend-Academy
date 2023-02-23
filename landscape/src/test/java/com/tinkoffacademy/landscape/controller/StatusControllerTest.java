package com.tinkoffacademy.landscape.controller;

import com.google.protobuf.Empty;
import com.tinkoffacademy.landscape.service.StatusService;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.tinkoff.proto.ReadinessResponse;
import ru.tinkoff.proto.StatusServiceGrpc.StatusServiceBlockingStub;
import ru.tinkoff.proto.VersionResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class StatusControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StatusService statusService;

    private static final ReadinessResponse handymanReadiness = ReadinessResponse
            .newBuilder()
            .setStatus("OK")
            .build();
    private static final ReadinessResponse rancherReadiness = ReadinessResponse
            .newBuilder()
            .setStatus("OK")
            .build();
    private static final VersionResponse handymanVersion = VersionResponse
            .newBuilder()
            .setName("handymanService")
            .setGroup("ru.tinkoff")
            .setArtifact("handymanService")
            .setVersion("1.0.0")
            .build();
    private static final VersionResponse rancherVersion = VersionResponse
            .newBuilder()
            .setName("rancherService")
            .setGroup("ru.tinkoff")
            .setArtifact("rancherService")
            .setVersion("1.0.0")
            .build();

    private static final ManagedChannel handymanChannel = ManagedChannelBuilder
            .forAddress("localhost", 8090)
            .build();

    private static final ManagedChannel rancherChannel = ManagedChannelBuilder
            .forAddress("localhost", 8091)
            .build();
    StatusServiceBlockingStub handymanStub = mock(StatusServiceBlockingStub.class);

    StatusServiceBlockingStub rancherStub = mock(StatusServiceBlockingStub.class);

    @BeforeEach
    public void setup() {
        when(handymanStub.getVersion(Empty.getDefaultInstance())).thenReturn(handymanVersion);
        when(rancherStub.getVersion(Empty.getDefaultInstance())).thenReturn(rancherVersion);
        when(handymanStub.getReadiness(Empty.getDefaultInstance())).thenReturn(handymanReadiness);
        when(rancherStub.getReadiness(Empty.getDefaultInstance())).thenReturn(rancherReadiness);
        when(handymanStub.getChannel()).thenReturn(handymanChannel);
        when(rancherStub.getChannel()).thenReturn(rancherChannel);
        statusService.setHandymanStub(handymanStub);
        statusService.setRancherStub(rancherStub);
    }

    @Test
    public void testGetStatuses() throws Exception {
        // Given
        String statusesUrl = "/servers/statuses";

        // When
        ResultActions response = mockMvc.perform(get(statusesUrl));

        // Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                               "HandymanService": [
                                   {  \s
                                       "host" : "localhost:8090",
                                       "status" : "OK",
                                       "artifact" : "handymanService",
                                       "name" : "handymanService",
                                       "group" : "ru.tinkoff",
                                       "version" : "1.0.0" \s
                                   }
                               ],
                                "RancherService": [
                                   {  \s
                                       "host" : "localhost:8091",
                                       "status" : "OK",
                                       "artifact" : "rancherService",
                                       "name" : "rancherService",
                                       "group" : "ru.tinkoff",
                                       "version" : "1.0.0"\s
                                   }
                                ]\s
                           }
                        """));
    }
}