package com.scheduling.serviceImplimentation;

import java.io.ByteArrayInputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AwsService {
    
    private AwsService() {
    }
    
    private static BasicAWSCredentials creds = new BasicAWSCredentials("AKIAXxxxxxxxxxxxxxxxxLA",
           XxxxxxxxxxxxxxxxxxxxxxxL+xxxxxxxxxx

qg");
    private static AmazonS3 s3 = AmazonS3Client.builder().withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(creds)).build();

    public static String uploadimage(String filename,byte[] data) {
        PutObjectRequest objectRequest = new PutObjectRequest("com.jntalks.post.pic", filename,  new ByteArrayInputStream(data), null);
        objectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        s3.putObject(objectRequest);
        String link = s3.getUrl("com.jntalks.post.pic", filename).toString();
        System.out.println("fileuploaded S3");
        return link;
    }
}
