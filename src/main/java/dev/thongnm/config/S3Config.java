package dev.thongnm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "aws")
public class S3Config {
    private String region;

    private String accessKey;

    private String secretKey;

    private String bucketName;
}
