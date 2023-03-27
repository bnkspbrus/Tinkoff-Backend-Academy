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
    @ManyToOne(optional = false)
    @JoinColumn(name = "type_v2")
    private AccountTypeV2 typeV2;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    private LocalDateTime creation;
    private LocalDateTime updating;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
}
