package com.vnshita.ELearningWebApp.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
public class S3Service {

    private final AmazonS3 s3Client;
    private final String bucketName;

    public S3Service(
            @Value("${aws.access.key.id}") String accessKeyId,
            @Value("${aws.secret.access.key}") String secretAccessKey,
            @Value("${aws.region}") String region,
            @Value("${aws.s3.bucket}") String bucketName) {

        this.bucketName = bucketName;
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, secretAccessKey);
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    public String uploadFile(String key, InputStream inputStream, long contentLength, String contentType) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(contentLength);
        metadata.setContentType(contentType);

        s3Client.putObject(new PutObjectRequest(bucketName, key, inputStream, metadata));
        return s3Client.getUrl(bucketName, key).toString();
    }


    public S3Object getObject(String key) {
        return s3Client.getObject(bucketName, key);
    }

    public URL generatePresignedUrl(String key, long expirationInDays) {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += expirationInDays * 24L * 60L * 60L * 1000L; // expirationInDays in milliseconds
        expiration.setTime(expTimeMillis);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, key)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);

        return s3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }

}
