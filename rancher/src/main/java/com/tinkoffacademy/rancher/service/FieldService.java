package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.dto.FieldDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final GardenerService gardenerService;

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
}
