package com.tinkoffacademy.landscape.entity;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.tinkoffacademy.landscape.enums.Skill;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, exclude = "userAccounts")
@ToString(callSuper = true, exclude = "userAccounts")
public class User extends Account {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAccount> userAccounts = new ArrayList<>();
    private byte[] photo;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Skill> skills;

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts.forEach(userAccount -> userAccount.setUser(null));
        this.userAccounts.clear();
        this.userAccounts.addAll(userAccounts);
        this.userAccounts.forEach(userAccount -> userAccount.setUser(this));
    }
}
