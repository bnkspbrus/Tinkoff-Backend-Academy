package com.tinkoffacademy.rancher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * DTO for Account
 * <p>
 * This class is used to transfer data from the database to the client and vice versa.
 * It is used in the {@link com.tinkoffacademy.rancher.controller.AccountController}.
 * </p>
 *
 * @param id        - id of the account. Ignored by Jackson if null while serializing. Ignored while deserializing.
 * @param login     - login of the account
 * @param email     - email of the account
 * @param phone     - phone of the account
 * @param latitude  - latitude of the account
 * @param longitude - longitude of the account
 * @param skills    - skills of the account
 * @see com.tinkoffacademy.rancher.model.Account
 * @see com.tinkoffacademy.rancher.controller.AccountController
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
