package com.tinkoffacademy.rancher.controller;

import com.tinkoffacademy.rancher.dto.FieldDto;
import com.tinkoffacademy.rancher.service.FieldService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;

    @GetMapping("/{id}")
    @Timed(value = "getById.time", description = "Time taken to get field by id")
    public FieldDto findById(@PathVariable Long id) {
        return fieldService.findById(id);
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to find all fields")
    public List<FieldDto> findAll() {
        return fieldService.findAll();
    }

    @PostMapping
    @Timed(value = "save.time", description = "Time taken to save field")
    public FieldDto save(@RequestBody FieldDto field) throws ParseException {
        return fieldService.save(field);
    }

    @PutMapping("/{id}")
    @Timed(value = "updateById.time", description = "Time taken to update field by id")
    public FieldDto updateById(@PathVariable Long id, @RequestBody FieldDto field) throws ParseException {
        return fieldService.updateById(id, field);
    }

    @DeleteMapping("/{id}")
    @Timed(value = "deleteById.time", description = "Time taken to delete field by id")
    public void deleteById(@PathVariable Long id) {
        fieldService.deleteById(id);
    }
}
