package dev.thongnm.utils;

import dev.thongnm.config.ConfigPath;
import dev.thongnm.config.S3Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({S3Config.class, ConfigPath.class})
public class AppConfigBeans {

}
