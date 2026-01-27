package dev.thongnm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig {
    private String name;

    private int width;

    private int height;

    private boolean allowMaximum = true;

    private boolean allowMinimum = true;

    private String startupView;
}
