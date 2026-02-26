package com.rituals.basket.pooja.market.data.service.core.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private static final String BUCKET_NAME = "rituals-basket";
    private final S3Client s3Client;

    public byte[] getImage(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }

    public byte[] getVideo(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(getObjectRequest);
        return objectBytes.asByteArray();
    }

    public String getImageUrl(String key) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", BUCKET_NAME, "ap-south-1", key);
    }

    public String uploadFile(MultipartFile file, String fileName) throws IOException {
        String key;

        if (fileName != null && !fileName.trim().isEmpty()) {
            key = fileName;
        } else {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.trim().isEmpty()) {
                key = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
            } else {
                return "";
            }
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(key)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return key;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        return uploadFile(file, null);
    }
}
