package com.project.crewwebproject.service;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.crewwebproject.exception.PrivateException;
import com.project.crewwebproject.exception.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public List<String> uploadFiles(List<MultipartFile> multipartFileList) {
        if (multipartFileList == null) {
            return null;
        }

        List<String> imgUrlList = new ArrayList<>();

        // forEach ????????? ?????? multipartFile??? ????????? ????????? ????????? fileNameList??? ??????
        multipartFileList.forEach(file -> {
            if(file.getContentType() == null || !(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")))
                throw new PrivateException(StatusCode.INTERNAL_SERVER_ERROR_PLZ_CHECK);

            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                String imgUrl = amazonS3.getUrl(bucket, fileName).toString();
                imgUrlList.add(imgUrl);
                System.out.println("IMG ?????? : " + imgUrl);
            } catch(IOException e) {
                throw new PrivateException(StatusCode.INTERNAL_SERVER_ERROR_PLZ_CHECK);
            }
        });

        return imgUrlList;
    }

    public void deleteAllWithImgPathList(List<String> imgPathList) {
        try {
            imgPathList.stream().forEach(imgPath -> amazonS3Client().deleteObject(new DeleteObjectRequest(bucket, imgPath.split("amazonaws.com/")[1])));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }


    public void deleteFile(String fileName) {
        amazonS3Client().deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    private String createFileName(String fileName) { // ?????? ?????? ????????? ???, ???????????? ??????????????? ?????? random?????? ????????????.
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) { // file ????????? ????????? ????????? ???????????? ?????? ???????????? ????????????, ?????? ????????? ???????????? ???????????? ??? ?????? ?????? ?????? .??? ?????? ????????? ?????????????????????.
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "????????? ????????? ??????(" + fileName + ") ?????????.");
        }
    }
}
