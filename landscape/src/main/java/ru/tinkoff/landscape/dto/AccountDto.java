package ru.tinkoff.landscape.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UserDto.class, name = "handyman"),
        @JsonSubTypes.Type(value = GardenerDto.class, name = "rancher")
})
@Data
public abstract class AccountDto {
    private Long id;
    private String type;
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
