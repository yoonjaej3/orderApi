package com.jyj.api.status.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jyj.api.common.configuration.EnvironmentComponent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class HealthResponse {
    private String name;
    private String version;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime timestamp;

    public HealthResponse() {
        this.name = EnvironmentComponent.PROJECT_NAME;
        this.version = EnvironmentComponent.VERSION;
        this.timestamp = LocalDateTime.now();
    }
}
