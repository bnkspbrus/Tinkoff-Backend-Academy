package com.tinkoffacademy.landscape.entity;

import lombok.*;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(exclude = "gardener")
@ToString(exclude = "gardener")
@AllArgsConstructor
@NoArgsConstructor
@With
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gardener gardener;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;
    private Geometry area;
}
