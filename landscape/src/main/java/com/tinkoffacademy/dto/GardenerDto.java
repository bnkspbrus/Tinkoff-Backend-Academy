package com.tinkoffacademy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class GardenerDto extends AccountDto {
    private List<FieldDto> fields;
}
