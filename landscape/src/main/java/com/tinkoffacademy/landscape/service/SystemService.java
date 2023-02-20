package com.tinkoffacademy.landscape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final BuildProperties buildProperties;

    public Map<String, String> getReadiness() {
        return Map.of(buildProperties.getName(), "OK");
    }
}
