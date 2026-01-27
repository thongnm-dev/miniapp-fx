package dev.thongnm.service;

import dev.thongnm.config.S3Config;
import dev.thongnm.model.AwsItem;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class S3Service {

    private final S3Config s3Config;

    public S3Service (S3Config config) {
        this.s3Config = config;
    }

    public List<AwsItem> load(final Map<String, Object> params) {

        List<AwsItem> results = new ArrayList<>();

        try (S3Client client = S3Client.builder().region(Region.of(s3Config.getRegion())).build()) {

            final String url = (String) params.get("url");
            ListObjectsV2Request listObjectsReq = ListObjectsV2Request.builder()
                                        .bucket(s3Config.getBucketName())
                                        .prefix(url)
                                        .build();

            ListObjectsV2Response response = client.listObjectsV2(listObjectsReq);

            for (S3Object s3Object : response.contents()) {

            }
        }

        return results;
    }
}
