package com.tinkoffacademy.landscape.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GardenerDto extends AccountDto {
    private List<FieldDto> fields;
}
