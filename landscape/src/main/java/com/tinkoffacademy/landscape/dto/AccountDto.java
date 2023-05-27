package com.tinkoffacademy.landscape.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class AccountDto {
        private UUID id;
        private String typeName;
        @NotBlank(message = "Login is mandatory")
        private String login;
        @Email(message = "Email should be valid")
        private String email;
        @NotBlank(message = "Phone is mandatory")
        private String phone;
        @NotNull(message = "Latitude is mandatory")
        private Double latitude;
        @NotNull(message = "Longitude is mandatory")
        private Double longitude;
}
