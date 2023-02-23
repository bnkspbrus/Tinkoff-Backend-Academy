package com.tinkoffacademy.landscape.controller;

import com.tinkoffacademy.landscape.dto.StatusDTO;
import com.tinkoffacademy.landscape.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/servers")
public class StatusController {

    private final StatusService statusService;

    /**
     * Gets status for each server
     * @return Map where key is server name and value is statuses of such server instances
     */
    @GetMapping("/statuses")
    public Map<String, StatusDTO[]> getStatuses() {
        return statusService.getStatuses();
    }
}
