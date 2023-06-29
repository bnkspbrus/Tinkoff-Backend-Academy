package ru.tinkoff.landscape.controller;

import ru.tinkoff.landscape.service.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SystemController.class, properties = "spring.datasource.url=none")
@Import(SystemService.class)
class SystemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildProperties buildProperties;

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
        when(buildProperties.getName()).thenReturn("LandscapeService");
        String readinessUrl = "/system/readiness";

        // When
        ResultActions response = mockMvc.perform(get(readinessUrl));

        // Then
        response
                .andExpect(status().isOk())
                .andExpect(content().json("{\"LandscapeService\": \"OK\"}"));
    }
}