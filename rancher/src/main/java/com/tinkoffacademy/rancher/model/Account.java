package com.tinkoffacademy.rancher.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("handymanAccounts")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private List<String> skills;
    private Double latitude;
    private Double longitude;
    private String parentUUID;
}
