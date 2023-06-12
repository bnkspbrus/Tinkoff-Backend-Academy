package com.tinkoffacademy.landscape.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tinkoffacademy.landscape.enums.AccountType;
import lombok.Data;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDto.class, name = "handyman"),
        @JsonSubTypes.Type(value = GardenerDto.class, name = "rancher")
})
@Data
@JsonIgnoreProperties(value = {"id", "creation", "updating"}, allowGetters = true)
public class AccountDto {
    private Long id;
    private AccountType type;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    private Double latitude;
    private Double longitude;
}
