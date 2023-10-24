package com.group2.secotool_app.bussiness.service.Impl;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.group2.secotool_app.bussiness.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
public class FileServiceImpl implements IFileService {
    @Override
    public void validateFilesAreImages(List<MultipartFile> files) {
        files.forEach(file -> {
            String filename = Objects.requireNonNull(file.getOriginalFilename()).toLowerCase();
            //check if the file is empty
            if (file.isEmpty()) {
                throw new IllegalStateException("Cannot upload empty file");
            }
            //Check if the file is an image
            if (!(filename.endsWith(".jpg")
                    || filename.endsWith(".png")
                    || filename.endsWith(".gif")
                    || filename.endsWith(".jpeg"))) {
                throw new IllegalStateException("FIle uploaded is not an image");
            }
        });
    }

    @Override
    public ObjectMetadata getFileMetadata(MultipartFile file) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        return objectMetadata;
    }
}
