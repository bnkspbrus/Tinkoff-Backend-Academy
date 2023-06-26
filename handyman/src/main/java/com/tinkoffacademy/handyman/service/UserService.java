package com.tinkoffacademy.handyman.service;

import com.tinkoffacademy.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

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

    public List<UserDto> getAllUsers() {
        return webClient.get()
                .uri("/accounts?type=handyman")
                .retrieve()
                .bodyToFlux(UserDto.class)
                .collectList()
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

    public UserDto updateUser(UserDto userDto) {
        return webClient.put()
                .uri("/accounts/{id}", userDto.getId())
                .bodyValue(userDto)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public void deleteUser(Long id) {
        webClient.delete()
                .uri("/accounts/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public UserDto getUserByUserAccountId(Long userAccountId) {
        return webClient.get()
                .uri("/accounts/user-account/{id}", userAccountId)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }

    public boolean acceptOrder(Long id, Long orderId) {
        Random random = new Random();
        boolean isAccepted = random.nextBoolean();
        if (isAccepted) {
            new Thread(() -> {
                try {
                    Thread.sleep(random.nextLong(1000, 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                webClient.put()
                        .uri("/orders/{id}/complete", orderId)
                        .retrieve()
                        .bodyToMono(Void.class)
                        .retry(1) // retry in case of failure
                        .block();
            }).start();
        }
        return isAccepted;
    }
}
