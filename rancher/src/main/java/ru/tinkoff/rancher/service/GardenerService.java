package ru.tinkoff.rancher.service;

import ru.tinkoff.rancher.dto.GardenerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class GardenerService {
    private final WebClient webClient;

    public GardenerService(@Value("${landscape.baseUrl}") String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public GardenerDto getGardenerById(Long id) {
        return webClient.get()
                .uri("/accounts/{id}", id)
                .retrieve()
                .bodyToMono(GardenerDto.class)
                .block();
    }

    public List<GardenerDto> getAllGardeners() {
        return webClient.get()
                .uri("/accounts?type=rancher")
                .retrieve()
                .bodyToFlux(GardenerDto.class)
                .collectList()
                .block();
    }

    public GardenerDto saveGardener(GardenerDto gardenerDto) {
        return webClient.post()
                .uri("/accounts")
                .bodyValue(gardenerDto)
                .retrieve()
                .bodyToMono(GardenerDto.class)
                .block();
    }

    public GardenerDto updateGardener(GardenerDto gardenerDto) {
        return webClient.put()
                .uri("/accounts/{id}", gardenerDto.getId())
                .bodyValue(gardenerDto)
                .retrieve()
                .bodyToMono(GardenerDto.class)
                .block();
    }

    public void deleteGardener(Long id) {
        webClient.delete()
                .uri("/accounts/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public GardenerDto getGardenerByFieldId(Long id) {
        return webClient.get()
                .uri("/accounts/field/{id}", id)
                .retrieve()
                .bodyToMono(GardenerDto.class)
                .block();
    }
}
