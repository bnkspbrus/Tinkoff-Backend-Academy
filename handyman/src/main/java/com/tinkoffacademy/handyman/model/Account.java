package com.tinkoffacademy.handyman.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("accounts")
@Getter
@Setter
@AllArgsConstructor
public class Account {
    @Id
    private String id;
    private String parentUUID;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
