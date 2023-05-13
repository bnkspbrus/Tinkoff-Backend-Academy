package com.tinkoffacademy.rancher.service;

import com.tinkoffacademy.rancher.dto.FieldDto;
import com.tinkoffacademy.rancher.entity.Field;
import com.tinkoffacademy.rancher.entity.Gardener;
import com.tinkoffacademy.rancher.repository.FieldRepository;
import com.tinkoffacademy.rancher.repository.GardenerRepository;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FieldService {
    private final ModelMapper modelMapper;
    private final FieldRepository fieldRepository;
    private final GardenerRepository gardenerRepository;

    public FieldDto findById(Long id) {
        Field field = fieldRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Field with id " + id + " not found"
                ));
        return toFieldDto(field);
    }

    public List<FieldDto> findAll() {
        return fieldRepository
                .findAll()
                .stream()
                .map(this::toFieldDto)
                .peek(fieldDto -> fieldDto.setId(null))
                .toList();
    }

    public FieldDto save(FieldDto fieldDto) throws ParseException {
        Field field = toField(fieldDto);
        field = fieldRepository.save(field);
        return toFieldDto(field);
    }

    public FieldDto updateById(Long id, FieldDto fieldDto) throws ParseException {
        Field field = toField(fieldDto);
        field.setId(id);
        field = fieldRepository.save(field);
        return toFieldDto(field);
    }

    public void deleteById(Long id) {
        fieldRepository.deleteById(id);
    }

    private FieldDto toFieldDto(Field field) {
        FieldDto fieldDto = modelMapper.map(field, FieldDto.class);
        fieldDto.setGardenerId(field.getGardener().getId());
        fieldDto.setAreaWKT(field.getArea().toText());
        return fieldDto;
    }

    private Field toField(FieldDto fieldDto) throws ParseException {
        Field field = modelMapper.map(fieldDto, Field.class);
        Gardener gardener = gardenerRepository
                .findById(fieldDto.getGardenerId())
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "Gardener with id " + fieldDto.getGardenerId() + " not found"
                ));
        field.setGardener(gardener);
        field.setArea(new WKTReader().read(fieldDto.getAreaWKT()));
        return field;
    }
}
