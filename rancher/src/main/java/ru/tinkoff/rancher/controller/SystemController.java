package ru.tinkoff.rancher.controller;

import java.util.Map.Entry;

import ru.tinkoff.rancher.service.SystemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public record SystemController(
        SystemService systemService
) {
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
