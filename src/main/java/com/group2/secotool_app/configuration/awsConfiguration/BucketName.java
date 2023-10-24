package com.group2.secotool_app.configuration.awsConfiguration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BucketName {
    BUCKET_NAME("secotoolbucket");
    private final String bucketName;
}
