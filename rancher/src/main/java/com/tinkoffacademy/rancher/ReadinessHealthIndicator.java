package com.tinkoffacademy.rancher;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "readiness")
public class ReadinessHealthIndicator {
    @ReadOperation
    public ReadinessHealth health() {
        return new ReadinessHealth(Map.of("RancherService", "OK"));
    }
}
