package com.tinkoffacademy.rancher;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ReadinessHealth(Map<String, Object> details) {

    @Override
    @JsonAnyGetter
    public Map<String, Object> details() {
        return this.details;
    }
}