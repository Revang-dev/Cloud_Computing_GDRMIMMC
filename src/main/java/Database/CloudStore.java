package Database;

import Entity.Files;
import Entity.Users;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import mail.MailSender;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class CloudStore {
    private static Storage storage = null;
    private String bucket = "brave-sonar-218511.appspot.com";

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    public String uploadFile(String file,byte[] fichier){

        BlobInfo blobInfo =
                storage.create(
                        BlobInfo
                                .newBuilder(this.bucket, file)
                                // Modify access list to allow all users with link to read file
                                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
                                .build(),
                        fichier);
        // return the public download link
        return blobInfo.getMediaLink();
    }

    public void deleteFile(String blobName) throws FileNotFoundException, IOException {

        storage.delete(BlobInfo
                .newBuilder(bucket, blobName)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build().getBlobId());
    }

    public void downloadFile(Users user, Files blobName) throws FileNotFoundException, IOException{

        BlobInfo blobInfo = storage.get(BlobInfo
                .newBuilder(bucket, blobName.getName())
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build().getBlobId());
        URL signedUrl = storage.signUrl(BlobInfo.newBuilder(bucket, blobName.getName()).build(),
                5, TimeUnit.MINUTES,
                Storage.SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
                        new FileInputStream(blobInfo.getMediaLink()))));
        MailSender.SendLinkTo(user.getEmail(), signedUrl.toString());

    }

    public void deleteAll(ArrayList<Files> blobNameList){
        for(Files blobName : blobNameList){
            storage.delete(BlobInfo
                    .newBuilder(bucket, blobName.getName())
                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                    .build().getBlobId());
        }
    }
}
