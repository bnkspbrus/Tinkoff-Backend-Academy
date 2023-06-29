package ru.tinkoff.landscape.controller;

import ru.tinkoff.landscape.service.StatusService;
import ru.tinkoff.landscape.status.StatusCollector;
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
     *
     * @return all handyman statuses and all rancher statuses
     */
    @GetMapping("/statuses")
    public StatusCollector getStatuses() {
        return statusService.getStatuses();
    }
}
