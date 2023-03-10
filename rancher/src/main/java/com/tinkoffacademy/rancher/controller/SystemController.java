package com.tinkoffacademy.rancher.controller;

import java.util.Map.Entry;

import com.tinkoffacademy.rancher.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    /**
     * Checks liveness
     */
    @GetMapping("/liveness")
    public void getLiveness() {
    }

    /**
     * Gets readiness state
     *
     * @return pair of server name and state of connectivity
     */
    @GetMapping("/readiness")
    public Entry<String, String> getReadiness() {
        return systemService.getReadiness();
    }
}
