package ru.tinkoff.landscape.entity;

import ru.tinkoff.landscape.enums.Skill;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true)
public class User extends Account {
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
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
