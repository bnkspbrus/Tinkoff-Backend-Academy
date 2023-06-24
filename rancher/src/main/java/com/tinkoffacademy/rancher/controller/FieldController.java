package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.rancher.dto.FieldDto;
import com.tinkoffacademy.rancher.dto.OrderDto;
import com.tinkoffacademy.rancher.service.FieldService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fields")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;

    @GetMapping("{id}")
    @Timed(value = "getById.time", description = "Time taken to get field by id")
    public FieldDto getFieldById(@PathVariable Long id) {
        return fieldService.getFieldById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all fields")
    public List<FieldDto> getAllFields() {
        return fieldService.getAllFields();
    }

    @PostMapping("/gardener/{gardenerId}")
    @Timed(value = "save.time", description = "Time taken to save field with gardener id")
    public FieldDto saveFieldWithGardenerId(@PathVariable Long gardenerId, @RequestBody FieldDto fieldDto) {
        return fieldService.saveFieldWithGardenerId(gardenerId, fieldDto);
    }

    @PutMapping
    @Timed(value = "updateById.time", description = "Time taken to update field by id")
    public FieldDto updateField(@RequestBody FieldDto fieldDto) {
        return fieldService.updateField(fieldDto);
    }

    @DeleteMapping("{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete field by id")
    public void deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
    }

    @PostMapping("/{id}/orders/create")
    public OrderDto createOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {
        return fieldService.createOrder(id, orderDto);
    }
}
