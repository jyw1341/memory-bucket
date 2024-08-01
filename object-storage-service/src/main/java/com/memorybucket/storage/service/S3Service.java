package com.memorybucket.storage.service;

import com.memorybucket.storage.config.S3ConfigurationProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3Service implements ObjectStorageService {

    private final S3ConfigurationProperties s3ConfigurationProperties;
    private final S3Presigner s3Presigner;
    private final S3Client s3Client;
    private final S3AsyncClient s3AsyncClient;

    @Override
    public String getUploadUrl(String key) {
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(s3ConfigurationProperties.getBucketName())
                .key(key)
                .build();
        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(1))
                .putObjectRequest(objectRequest)
                .build();
        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        return presignedRequest.url().toExternalForm();
    }

    @Override
    public void deleteObject(String key) {
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(s3ConfigurationProperties.getBucketName())
                .key(key)
                .build();
        s3AsyncClient.deleteObject(deleteObjectRequest);
    }
}
