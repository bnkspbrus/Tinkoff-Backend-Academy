package com.tinkoffacademy.handyman.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/system")
public class HandymanController {
    /**
     * Checks liveness
     */
    @GetMapping("/liveness")
    public void getLiveness() {}

    /**
     * Checks readiness
     * @return Map with single entry to be converted in Json
     */
    @GetMapping("/readiness")
    public Map<String, String> getReadiness() {
        return Map.of("HandymanService", "OK");
    }
}
