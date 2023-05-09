package com.tinkoffacademy.landscape.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class AccountDto {
    private Long id;
    private String typeName;
    private String login;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    private Double latitude;
    private Double longitude;
}
