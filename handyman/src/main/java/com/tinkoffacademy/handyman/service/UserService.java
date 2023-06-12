package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.handyman.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Uses WebClient to retrieve data from landscape service.
 */
@Service
public class UserService {
    private final WebClient webClient;

    public UserService(@Value("${landscape.baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public UserDto getUserById(Long id) {
        return webClient.get()
                .uri("/accounts/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public UserDto saveUser(UserDto userDto) {
        return webClient.post()
                .uri("/accounts")
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
