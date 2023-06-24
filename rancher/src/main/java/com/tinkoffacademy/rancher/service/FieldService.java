package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.rancher.dto.FieldDto;
import com.tinkoffacademy.rancher.dto.OrderDto;
import com.tinkoffacademy.rancher.dto.Skill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

@Service
public class FieldService {
    private final GardenerService gardenerService;
    private final WebClient webClient;

    public FieldService(GardenerService gardenerService, @Value("${landscape.baseUrl}") String baseUrl) {
        this.gardenerService = gardenerService;
        this.webClient = WebClient.create(baseUrl);
    }

    public FieldDto getFieldById(Long id) {
        return gardenerService.getGardenerByFieldId(id)
                .getFields()
                .stream()
                .filter(field -> field.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Field not found"
                ));
    }

    public List<FieldDto> getAllFields() {
        return gardenerService.getAllGardeners()
                .stream()
                .flatMap(gardenerDto -> gardenerDto.getFields().stream())
                .toList();
    }

    public FieldDto saveFieldWithGardenerId(Long gardenerId, FieldDto fieldDto) {
        var gardenerDto = gardenerService.getGardenerById(gardenerId);
        gardenerDto.getFields().add(fieldDto);
        var updated = gardenerService.updateGardener(gardenerDto);
        return updated.getFields().get(updated.getFields().size() - 1);
    }

    public FieldDto updateField(FieldDto fieldDto) {
        var gardenerDto = gardenerService.getGardenerByFieldId(fieldDto.getId());
        var fieldDtos = gardenerDto.getFields();
        var indexes = fieldDtos.stream().map(FieldDto::getId).toList();
        var index = indexes.indexOf(fieldDto.getId());
        fieldDtos.set(index, fieldDto);
        var updated = gardenerService.updateGardener(gardenerDto);
        return updated.getFields().get(index);
    }

    public void deleteField(Long id) {
        var gardenerDto = gardenerService.getGardenerByFieldId(id);
        var fieldDtos = gardenerDto.getFields();
        var indexes = fieldDtos.stream().map(FieldDto::getId).toList();
        var index = indexes.indexOf(id);
        fieldDtos.remove(index);
        gardenerService.updateGardener(gardenerDto);
    }

    public OrderDto createOrder(Long id, OrderDto orderDto) {
        orderDto.setGardenId(id);
        return webClient.post()
                .uri("/orders")
                .bodyValue(orderDto)
                .retrieve()
                .bodyToMono(OrderDto.class)
                .block();
    }
}
