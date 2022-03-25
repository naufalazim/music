package com.project.musicappbackend.services;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {
    //Endpoints: sgp1.digitaloceanspaces.com

    //give aws s3 object:
    private final AmazonS3 space;

    public StorageService() {

        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials("5WC5NMWRKUBQJV5TQLOY", "gdb0sUrm1noFJ6YbWi2ZSOmE8HYQqU11l4+fDk2ej3I")
        );

        space = AmazonS3ClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(
                        new AwsClientBuilder.EndpointConfiguration("sgp1.digitaloceanspaces.com", "sgp1")
                )
                .build();

    }

    //list all files:
    public List<String> getSongFileNames() {

        ListObjectsV2Result result = space.listObjectsV2("music-player");
        List<S3ObjectSummary> objects = result.getObjectSummaries();

        return objects.stream()
                .map(s3ObjectSummary -> {
                    return s3ObjectSummary.getKey();
                }).collect(Collectors.toList());


                //void:
//                .forEach(s3ObjectSummary -> {
//                    System.out.println(s3ObjectSummary.toString());
//                });

    }

    //UPLOAD FILES PART:
    public void uploadSong(MultipartFile file) throws IOException {

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(file.getContentType());
            space.putObject(new PutObjectRequest("music-player", file.getOriginalFilename(), file.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));

            //.withCannedAcl(CannedAccessControlList.PublicRead) -> public as default

    }

}
