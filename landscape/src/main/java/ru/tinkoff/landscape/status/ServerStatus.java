package ru.tinkoff.landscape.status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServerStatus {
    private String host;
    private String status;
    private String artifact;
    private String name;
    private String group;
    private String version;
}
