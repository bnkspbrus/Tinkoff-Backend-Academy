package com.tinkoffacademy.rancher.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    private String id;
    private Long parentId;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
