package ru.tinkoff.landscape.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Gardener extends Account {
    @OneToMany(mappedBy = "gardener", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields = new ArrayList<>();

    public void setFields(List<Field> fields) {
        this.fields.forEach(field -> field.setGardener(null));
        this.fields.clear();
        this.fields.addAll(fields);
        this.fields.forEach(field -> field.setGardener(this));
    }
}
