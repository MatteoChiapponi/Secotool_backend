package com.group2.secotool_app.bussiness.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFileService {
    void validateFilesAreImages(List<MultipartFile> files);
    ObjectMetadata getFileMetadata(MultipartFile file);
}
