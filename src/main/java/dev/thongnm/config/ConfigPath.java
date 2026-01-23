package dev.thongnm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class ConfigPath {
    private String name;

    private int width;

    private int height;

    private boolean allowMaximumSize = true;

    private boolean allowMinimumSize = true;

    private String startupView;
}
