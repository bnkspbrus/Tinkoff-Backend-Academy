package com.tinkoffacademy.landscape.entity;

import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.*;

import com.tinkoffacademy.landscape.enums.Skill;
import com.tinkoffacademy.landscape.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private Field garden;
    @ManyToOne(optional = false)
    private User user;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime creation;
}
