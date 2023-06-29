package ru.tinkoff.landscape.service;

import java.util.Map.Entry;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import static java.util.Map.entry;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final BuildProperties buildProperties;

    /**
     * Gets readiness state
     *
     * @return pair of server name and "OK"
     */
    public Entry<String, String> getReadiness() {
        return entry(buildProperties.getName(), "OK");
    }
}
