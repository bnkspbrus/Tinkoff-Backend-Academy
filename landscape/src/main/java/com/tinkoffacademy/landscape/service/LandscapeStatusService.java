package com.tinkoffacademy.landscape.service;

import com.tinkoffacademy.landscape.dto.StatusDTO;
import com.tinkofflibrary.lib.HandymanStatuses;
import com.tinkofflibrary.lib.Status;
import com.tinkofflibrary.lib.StatusServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandscapeStatusService {

    private final ModelMapper modelMapper = new ModelMapper();
    @GrpcClient("handyman-server")
    private StatusServiceGrpc.StatusServiceBlockingStub statusServiceStub;

    public List<StatusDTO> getStatuses() {
        HandymanStatuses request = HandymanStatuses.getDefaultInstance();
        HandymanStatuses response = statusServiceStub.getStatus(request);
        return response
                .getHandymanServiceList()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private StatusDTO mapToDTO(Status status) {
        return StatusDTO
                .builder()
                .host(status.getHost())
                .status(status.getStatus())
                .artifact(status.getArtifact())
                .name(status.getName())
                .group(status.getGroup())
                .version(status.getVersion())
                .build();
    }
}
