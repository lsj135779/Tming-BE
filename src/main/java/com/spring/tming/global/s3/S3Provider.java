package com.spring.tming.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.spring.tming.global.validator.S3Validator;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3Provider {

    private final AmazonS3 amazonS3;
    private final String SEPARATOR = "/";

    @Value("${cloud.aws.s3.bucket.name}")
    public String bucket;

    private static ObjectMetadata setObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());
        return metadata;
    }

    public String saveFile(MultipartFile multipartFile, String folderName) throws IOException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        originalFilename =
                folderName
                        + SEPARATOR
                        + UUID.randomUUID()
                        + originalFilename.substring(originalFilename.lastIndexOf("."));
        createFolder(folderName);

        ObjectMetadata metadata = setObjectMetadata(multipartFile);

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }

    private void createFolder(String folderName) {
        if (!amazonS3.doesObjectExist(bucket, folderName)) {
            amazonS3.putObject(
                    bucket,
                    folderName + SEPARATOR,
                    new ByteArrayInputStream(new byte[0]),
                    new ObjectMetadata());
        }
    }

    public void deleteImage(String originalFilename) {
        if (originalFilename == null) {
            return;
        }
        S3Validator.validate(amazonS3, bucket, originalFilename);
        amazonS3.deleteObject(bucket, originalFilename);
    }

    public String updateImage(String originalFilename, MultipartFile multipartFile)
            throws IOException {
        S3Validator.validate(amazonS3, bucket, originalFilename);
        ObjectMetadata metadata = setObjectMetadata(multipartFile);

        amazonS3.putObject(bucket, originalFilename, multipartFile.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, originalFilename).toString();
    }
}
