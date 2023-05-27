package com.tinkoffacademy.landscape.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusCollector {
    @JsonProperty("HandymanService")
    private final List<ServerStatus> allHandyman = new ArrayList<>();
    @JsonProperty("RancherService")
    private final List<ServerStatus> allRancher = new ArrayList<>();

    public void addHandyman(ServerStatus status) {
        allHandyman.add(status);
    }

    public void addRancher(ServerStatus status) {
        allRancher.add(status);
    }
}
