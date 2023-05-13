package com.tinkoffacademy.rancher.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private Long gardenerId;
    private String address;
    private Double latitude;
    private Double longitude;
    private String areaWKT;
}
