package dev.thongnm.service;

import dev.thongnm.config.S3Config;
import dev.thongnm.model.AwsItem;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CommonPrefix;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class S3Service {

    private final S3Config s3Config;

    public S3Service(S3Config config) {
        this.s3Config = config;
    }

    public List<AwsItem> load(final Map<String, Object> params) {

        List<AwsItem> results = new ArrayList<>();

        AwsBasicCredentials credentials = AwsBasicCredentials.create(
                s3Config.getAccessKey(),
                s3Config.getSecretKey()
        );

        try (S3Client client = S3Client.builder()
                .region(Region.of(s3Config.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build()) {

            final String url = (String) params.get("url");

            ListObjectsV2Request listObjectsReq = ListObjectsV2Request.builder()
                    .bucket(s3Config.getBucketName())
                    .prefix(url)
                    .delimiter("/")
                    .build();

            ListObjectsV2Response response = client.listObjectsV2(listObjectsReq);

            for (CommonPrefix commonPrefix : response.commonPrefixes()) {
                AwsItem awsItem = new AwsItem();
                awsItem.setName(commonPrefix.prefix());
                results.add(awsItem);
            }
        }

        return results;
    }
}
