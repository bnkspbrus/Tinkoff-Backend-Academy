package com.tinkoffacademy.landscape.entity;

import com.tinkoffacademy.landscape.enums.Skill;
import com.tinkoffacademy.landscape.enums.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Objects;

@Entity
@Table(name="orders")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Field garden;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;
    @Type(type = "io.hypersistence.utils.hibernate.type.json.JsonType")
    @Column(columnDefinition = "jsonb")
    private EnumSet<Skill> skills;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    private LocalDateTime creation;

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        Class<?> oEffectiveClass = obj instanceof HibernateProxy ?
                ((HibernateProxy) obj).getHibernateLazyInitializer().getPersistentClass() : obj.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Order order = (Order) obj;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
