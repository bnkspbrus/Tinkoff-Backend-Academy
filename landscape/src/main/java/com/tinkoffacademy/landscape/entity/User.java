package com.tinkoffacademy.landscape.entity;

import com.tinkoffacademy.landscape.enums.Skill;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends Account {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAccount> userAccounts;
    private byte[] photo;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
        userAccounts.forEach(userAccount -> userAccount.setUser(this));
        if (getId() == null) {
            userAccounts.forEach(userAccount -> userAccount.setId(null));
        }
    }
}
