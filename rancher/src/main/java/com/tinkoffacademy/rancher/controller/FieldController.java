package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.rancher.dto.FieldDto;
import com.tinkoffacademy.rancher.service.FieldService;
import org.locationtech.jts.io.ParseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fields")
public record FieldController(
        FieldService fieldService
) {
    @GetMapping("/{id}")
    public FieldDto findById(@PathVariable Long id) {
        return fieldService.findById(id);
    }

    @GetMapping
    public List<FieldDto> findAll() {
        return fieldService.findAll();
    }

    @PostMapping
    public FieldDto save(@RequestBody FieldDto field) throws ParseException {
        return fieldService.save(field);
    }

    @PutMapping("/{id}")
    public FieldDto updateById(@PathVariable Long id, @RequestBody FieldDto field) throws ParseException {
        return fieldService.updateById(id, field);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        fieldService.deleteById(id);
    }
}
