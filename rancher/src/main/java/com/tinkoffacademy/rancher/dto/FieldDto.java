package com.tinkoffacademy.rancher.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.spatial4j.io.jackson.GeometryAsWKTSerializer;
import org.locationtech.spatial4j.io.jackson.GeometryDeserializer;

@Data
//@JsonIgnoreProperties(value = {"id"}, allowGetters = true)
public class FieldDto {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonSerialize(using = GeometryAsWKTSerializer.class)
    private Geometry area;
}
