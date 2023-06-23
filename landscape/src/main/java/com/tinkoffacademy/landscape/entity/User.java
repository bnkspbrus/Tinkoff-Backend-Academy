package com.tinkoffacademy.landscape.entity;

import com.tinkoffacademy.landscape.enums.Skill;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(callSuper = true)
public class User extends Account {
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UserAccount> userAccounts = new ArrayList<>();
    private byte[] photo;
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    @Column(columnDefinition = "jsonb")
    private EnumSet<Skill> skills;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    public void setUserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts.forEach(userAccount -> userAccount.setUser(null));
        this.userAccounts.clear();
        this.userAccounts.addAll(userAccounts);
        this.userAccounts.forEach(userAccount -> userAccount.setUser(this));
    }
}
