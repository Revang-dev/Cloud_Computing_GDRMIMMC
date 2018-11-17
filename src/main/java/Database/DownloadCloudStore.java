package Database;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.BlobSourceOption;
import com.google.cloud.storage.StorageOptions;
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
    
    public byte[] downloadFile(String blobName){

        BlobInfo blobInfo = storage.get(BlobId.of(bucket, blobName));
        
        byte[] file=storage.readAllBytes(blobInfo.getBlobId(),BlobSourceOption.generationMatch());
        
        return file;
    }
}
