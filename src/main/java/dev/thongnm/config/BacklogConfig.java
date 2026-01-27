package dev.thongnm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "backlog")
public class BacklogConfig {
    private String api;
}
