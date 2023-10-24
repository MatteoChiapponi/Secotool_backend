package com.group2.secotool_app.bussiness.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IBucketS3Service {
    List<String> storeFiles(List<MultipartFile> files);
}
