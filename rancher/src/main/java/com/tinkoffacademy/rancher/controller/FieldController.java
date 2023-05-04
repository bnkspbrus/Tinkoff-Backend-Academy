package com.tinkoffacademy.rancher.controller;

import java.util.List;

import com.tinkoffacademy.rancher.entity.Field;
import com.tinkoffacademy.rancher.service.FieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fields")
public record FieldController(
        FieldService fieldService
) {
    @GetMapping("/{id}")
    public Field findById(@PathVariable Long id) {
        return fieldService.findById(id);
    }

    @GetMapping
    public List<Field> findAll() {
        return fieldService.findAll();
    }

    @PostMapping
    public Field save(@RequestBody Field field) {
        return fieldService.save(field);
    }

    @PutMapping("/{id}")
    public Field updateById(@PathVariable Long id, @RequestBody Field field) {
        return fieldService.updateById(id, field);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        fieldService.deleteById(id);
    }
}
