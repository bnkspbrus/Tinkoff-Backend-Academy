package com.tinkoffacademy.handyman.dto;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

/**
 * DTO for Account
 * <p>
 * This class is used to transfer data from the database to the client and vice versa.
 * It is used in the {@link com.tinkoffacademy.handyman.controller.AccountController}.
 * </p>
 *
 * @param id        - id of the account. Ignored by Jackson if null while serializing. Ignored while deserializing.
 * @param login     - login of the account
 * @param email     - email of the account
 * @param phone     - phone of the account
 * @param latitude  - latitude of the account
 * @param longitude - longitude of the account
 * @param skills    - skills of the account
 * @see com.tinkoffacademy.handyman.model.Account
 * @see com.tinkoffacademy.handyman.controller.AccountController
 */
@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public record AccountDto(
        @JsonInclude(JsonInclude.Include.NON_NULL) String id,
        String login,
        String email,
        String phone,
        Double latitude,
        Double longitude,
        List<String> skills
) {
}
