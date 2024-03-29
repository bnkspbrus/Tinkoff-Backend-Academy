package ru.tinkoff.handyman.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.tinkoff.handyman.enums.Skill;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(value = {"type"}, allowGetters = true)
public class UserDto {
    private Long id;
    private String type = "handyman";
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    private Double latitude;
    private Double longitude;
    private byte[] photo;
    private Set<Skill> skills;
    private List<UserAccountDto> userAccounts;
}
