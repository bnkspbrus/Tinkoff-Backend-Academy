package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.service.StatusService;
import com.tinkoffacademy.landscape.status.StatusCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servers")
public class StatusController {

    private final StatusService statusService;

    /**
     * Gets status of each server
     * @return all handyman statuses and all rancher statuses
     */
    @GetMapping("/statuses")
    public StatusCollector getStatuses() {
        return statusService.getStatuses();
    }
}
