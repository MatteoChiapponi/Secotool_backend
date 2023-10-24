package com.group2.secotool_app.bussiness.service.Impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.group2.secotool_app.bussiness.service.IBucketS3Service;
import com.group2.secotool_app.bussiness.service.IFileService;
import com.group2.secotool_app.configuration.awsConfiguration.BucketName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BucketS3ServiceImpl implements IBucketS3Service {
    private final AmazonS3 bucket;
    private final IFileService imageValidationService;
    @Override
    public List<String> storeFiles(List<MultipartFile> files) throws RuntimeException{
        List<String> urls = new ArrayList<>();
        files.forEach(file -> {
            var metadata = imageValidationService.getFileMetadata(file);
            var bucketName = BucketName.BUCKET_NAME.getBucketName();
            String objectKey = String.valueOf(UUID.randomUUID());
            String url = String.format("https://%s.s3.amazonaws.com/%s",bucketName,objectKey);
            try {
                PutObjectRequest objectRequest = new PutObjectRequest(bucketName,objectKey,file.getInputStream(),metadata);
                bucket.putObject(objectRequest);
                urls.add(url);
            } catch (Exception e) {
                //mostrar error de aws
                System.out.println(e.getCause());
                System.out.println(e.getLocalizedMessage());
                System.out.println(e);

                throw new RuntimeException("failed to upload file "+file.getOriginalFilename());
            }
        });
        return urls;
    }
}
