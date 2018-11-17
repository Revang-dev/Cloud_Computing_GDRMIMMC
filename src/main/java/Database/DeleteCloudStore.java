package Database;

import com.google.cloud.storage.*;
import com.google.cloud.storage.Storage.SignUrlOption;

import Entity.Users;
import mail.MailSender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.google.auth.oauth2.ServiceAccountCredentials;

public class DeleteCloudStore {
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Database.DownloadCloudStore”,
     Body: {
         userID: Bob@gmail.com
         fileID: “Bob/myVideos/video5.mp4”
        }
    }
    */
    /////////////////////////////////////////////////////////////

    private static Storage storage = null;
    private String bucket = "brave-sonar-218511.appspot.com";

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }

    public void deleteFile(String blobName) throws FileNotFoundException, IOException{

        storage.delete(BlobInfo
                .newBuilder(bucket, blobName)
                .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build().getBlobId());
    }
}
