package com.tinkoffacademy.handyman.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for Account
 * <p>
 * This class is used to transfer data from the database to the client and vice versa.
 * It is used in the {@link com.tinkoffacademy.handyman.controller.AccountController}.
 * </p>
 *
 * @see com.tinkoffacademy.handyman.model.Account
 * @see com.tinkoffacademy.handyman.controller.AccountController
 */
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonInclude(Include.NON_NULL)
    private String id;
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
    private List<String> skills;
}
