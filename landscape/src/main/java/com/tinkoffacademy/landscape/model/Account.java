package com.tinkoffacademy.landscape.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "type_v2")
    private AccountTypeV2 typeV2;
    private String login;
    private String email;
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    private Double latitude;
    private Double longitude;
}
