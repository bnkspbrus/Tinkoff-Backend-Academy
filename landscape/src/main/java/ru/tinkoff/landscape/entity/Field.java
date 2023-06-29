package ru.tinkoff.landscape.entity;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@With
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ToString.Exclude
    private Gardener gardener;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    private Geometry area;

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        Class<?> oEffectiveClass = obj instanceof HibernateProxy ?
                ((HibernateProxy) obj).getHibernateLazyInitializer().getPersistentClass() : obj.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ?
                ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Field field = (Field) obj;
        return getId() != null && Objects.equals(getId(), field.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
