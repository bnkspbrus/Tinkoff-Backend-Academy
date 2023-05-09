package com.tinkoffacademy.handyman.dto;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for Account
 * <p>
 * This class is used to transfer data from the database to the client and vice versa.
 * It is used in the {@link com.tinkoffacademy.handyman.controller.AccountController}.
 * </p>
 *
 * @see com.tinkoffacademy.handyman.document.Account
 * @see com.tinkoffacademy.handyman.controller.AccountController
 */
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonInclude(Include.NON_NULL)
    private String id;
    private String login;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
