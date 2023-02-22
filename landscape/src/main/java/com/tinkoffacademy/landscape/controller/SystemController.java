package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.dto.StatusDTO;
import com.tinkoffacademy.landscape.service.StatusService;
import com.tinkoffacademy.landscape.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;

    private final StatusService statusService;

    /**
     * Checks liveness
     */
    @GetMapping("/liveness")
    public void getLiveness() {
    }

    /**
     * Checks readiness
     *
     * @return Map with single entry to be converted to Json
     */
    @GetMapping("/readiness")
    public Map<String, String> getReadiness() {
        return systemService.getReadiness();
    }

    /**
     * Gets status for each server
     * @return Map where key is server name and value is statuses of such server instances
     */
    @GetMapping("/statuses")
    public Map<String, StatusDTO[]> getStatuses() {
        return statusService.getStatuses();
    }
}
