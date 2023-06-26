package com.tinkoffacademy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(value = {"type"}, allowGetters = true)
public class GardenerDto {
    private Long id;
    private String type = "rancher";
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    private Double latitude;
    private Double longitude;
    private List<FieldDto> fields;
}
