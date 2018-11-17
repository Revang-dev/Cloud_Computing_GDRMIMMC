package Database;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;

import Entity.Users;
import mail.MailSender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;

public class DownloadCloudStore {
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
    private String bucket = "brave-sonar-218511.appspot.com/";

    static {
        storage = StorageOptions.getDefaultInstance().getService();
    }
    
    public void downloadFile(Users user,String blobName) throws FileNotFoundException, IOException{

        BlobInfo blobInfo = storage.get(BlobId.of(bucket, blobName));
        URL signedUrl = storage.signUrl(BlobInfo.newBuilder(bucket, blobName).build(), 
                5, TimeUnit.MINUTES, SignUrlOption.signWith(ServiceAccountCredentials.fromStream(
                new FileInputStream(blobInfo.getMediaLink()))));
        MailSender.SendLinkTo(user.getEmail(), signedUrl.toString());
        
    }
}
