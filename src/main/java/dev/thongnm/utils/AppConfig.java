package dev.thongnm.utils;

import dev.thongnm.config.ConfigPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class AppConfig {
    @Bean
    public ConfigPath configPathBean() {
        return new ConfigPath();
    }
}
