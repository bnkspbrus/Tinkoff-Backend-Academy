package com.tinkoffacademy.landscape.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tinkoffacademy.landscape.enums.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Account {
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserAccount> userAccounts;
    private byte[] photo;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;
}
