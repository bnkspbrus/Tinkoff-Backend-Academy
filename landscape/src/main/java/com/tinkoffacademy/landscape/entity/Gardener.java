package com.tinkoffacademy.landscape.entity;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
public class Gardener extends Account {
    @OneToMany(mappedBy = "gardener", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Field> fields;

    public void setFields(List<Field> fields) {
        this.fields = fields;
        fields.forEach(field -> field.setGardener(this));
        if (getId() == null) {
            fields.forEach(field -> field.setId(null));
        }
    }
}
