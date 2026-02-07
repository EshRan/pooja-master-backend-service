package com.rituals.basket.pooja.market.data.service.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    private static final String ACCESS_KEY = "AKIAXWEPWXGSVBYENGEQ";
    private static final String SECRET_KEY = "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";
    private static final Region REGION = Region.AP_SOUTH_1;

    @Bean
    public S3Client s3Client() {
        return S3Client.builder()
                .region(REGION)
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)))
                .build();
    }
}
