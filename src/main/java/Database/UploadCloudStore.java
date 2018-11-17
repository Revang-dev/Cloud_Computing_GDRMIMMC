package Database;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class UploadCloudStore {
    private static Storage storage = null;
    private String bucket = "brave-sonar-218511.appspot.com/";

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

}
