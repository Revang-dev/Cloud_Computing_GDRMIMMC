package Database;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import Entity.Users;
import mail.MailSender;

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
    
    public void downloadFile(Users user,String blobName){

        BlobInfo blobInfo = storage.get(BlobId.of(bucket, blobName));     
        MailSender.SendLinkTo(user.getEmail(), blobInfo.getMediaLink());
    }
}
