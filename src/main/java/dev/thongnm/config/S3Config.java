package dev.thongnm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "aws")
public class S3Config {
    private String region;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
