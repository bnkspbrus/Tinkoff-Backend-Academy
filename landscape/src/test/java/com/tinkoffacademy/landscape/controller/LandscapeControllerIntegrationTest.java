package com.tinkoffacademy.landscape.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LandscapeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp() {
        System.setProperty("LANDSCAPE_PORT", "9090");
    }

    @Test
    void testGetLiveness() throws Exception {
        // Given
        String livenessUrl = "/system/liveness";

        // When
        ResultActions response = mockMvc.perform(get(livenessUrl));

        // Then
        response.andExpect(status().isOk());
    }

    @Test
    void getReadiness() throws Exception {
        // Given
        String readinessUrl = "/system/readiness";

        // When
        ResultActions response = mockMvc.perform(get(readinessUrl));

        // Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json("{\"LandscapeService\": \"OK\"}"));
    }
}