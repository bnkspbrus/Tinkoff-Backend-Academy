package com.tinkoffacademy.rancher.service;

import java.util.List;

import com.tinkoffacademy.rancher.entity.Field;
import com.tinkoffacademy.rancher.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final FieldRepository fieldRepository;

    public Field findById(Long id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Field with id " + id + " not " +
                        "found"));
    }

    public List<Field> findAll() {
        return fieldRepository.findAll();
    }

    public Field save(Field field) {
        return fieldRepository.save(field);
    }

    public Field updateById(Long id, Field field) {
        field.setId(id);
        return fieldRepository.save(field);
    }

    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }
}
