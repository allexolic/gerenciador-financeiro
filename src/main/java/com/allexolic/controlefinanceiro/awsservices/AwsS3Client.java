package com.allexolic.controlefinanceiro.awsservices;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Component
public class AwsS3Client {

    @Value("${BUCKET_NAME}")
    private String bucketName;
    @Value("${ACCESS_KEY}")
    private String accessKey;
    @Value("${SECRET_KEY}")
    private String secretKey;
    private AmazonS3 s3;

    @PostConstruct
    private void initialize() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
        s3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.SA_EAST_1).build();
    }

    public void saveS3(String key_name, MultipartFile file) throws IOException {
        try{
            log.info("Upload file to S3");
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentLength(file.getSize());
            s3.putObject( bucketName, key_name, file.getInputStream(), objectMetadata );
            log.info("Upload file S3 successfully");
        } catch (AmazonServiceException e) {
            log.error("Error on upload file S3: " + e.getMessage());
            throw e;
        }
    }
}
