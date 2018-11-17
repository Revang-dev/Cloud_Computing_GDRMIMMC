package Database;

import Entity.Files;
import utils.JsonGenerator;

import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.cloud.datastore.*;

import java.util.ArrayList;


public class FileDataStore {
    //EmailUtilisateur, Url, Poids, Statut, DataCreation, Type, ID
    private static Datastore datastore;
    private static KeyFactory keyFactory;
    static private FileDataStore instance;

    private FileDataStore(){
        datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory = datastore.newKeyFactory().setKind("File2");
    }

    static public FileDataStore getInstance() {
        if (instance == null) {
            instance = new FileDataStore();
        }
        return instance;
    }

    public static void updateFile(Files file){
        Key key = keyFactory.newKey(file.getId());
        Entity entity = Entity.newBuilder(key)
                .set(Files.EMAIL, file.getEmailUtilisateur())
                .set(Files.URL, file.getUrl())
                .set(Files.WEIGHT, file.getWeight())
                .set(Files.DATE, file.getDateCreation())
                .set(Files.TYPE, file.getType())
                .set(Files.ID, file.getId())
                .set(Files.STATUT, file.getStatut())
                .build();
        datastore.update(entity);
    }

    public static void addFile(Files file){
        IncompleteKey key = keyFactory.newKey();
        FullEntity<IncompleteKey> fileData = Entity.newBuilder(key)
                .set(Files.EMAIL, file.getEmailUtilisateur())
                .set(Files.URL, file.getUrl())
                .set(Files.WEIGHT, file.getWeight())
                .set(Files.DATE, file.getDateCreation())
                .set(Files.TYPE, file.getType())
                .set(Files.ID, file.getId())
                .set(Files.STATUT, file.getStatut())
                .build();
        Entity newFile = datastore.add(fileData);
    }

    public static ArrayList<Entity.Files> getAllFile(){
        ArrayList<Entity.Files> res = new ArrayList<>();
        EntityQuery datastore_query = Query.newEntityQueryBuilder()
                .setKind("File2")
                .build();
        QueryResults<Entity> datastore_files = datastore.run(datastore_query);
        while (datastore_files.hasNext()) {
            Entity potencial_file = datastore_files.next();
            Entity.Files newFile = new Files(potencial_file.getString(Files.EMAIL), potencial_file.getString(Files.NAME),potencial_file.getString(Files.URL), Double.parseDouble(potencial_file.getString(Files.WEIGHT)), potencial_file.getString(Files.TYPE));
            res.add(newFile);
        }
        return res;
    }

    public static void deleteFile(String url){
        Files res = null;
        Entity potencial_file;
        EntityQuery datastore_query = Query.newEntityQueryBuilder()
                .setKind("File2")
                .build();
        QueryResults<Entity> datastore_files = datastore.run(datastore_query);
        while (datastore_files.hasNext()) {
            potencial_file = datastore_files.next();
            if (url.equals(potencial_file.getString(Files.URL))) {
                datastore.delete(potencial_file.getKey());
            }
        }
    }

    public static void deleteAll(){
        EntityQuery datastore_query = Query.newEntityQueryBuilder()
                .setKind("File2")
                .build();
        QueryResults<Entity> datastore_files = datastore.run(datastore_query);
        while (datastore_files.hasNext()) {
            Entity potencial_file = datastore_files.next();
            datastore.delete(potencial_file.getKey());
        }
    }

    public static Files getFile(String url){
        Files res = null;
        Entity potencial_file;

        EntityQuery datastore_query = Query.newEntityQueryBuilder()
                .setKind("File2")
                .build();
        QueryResults<Entity> datastore_files = datastore.run(datastore_query);

        while (datastore_files.hasNext()) {
            potencial_file = datastore_files.next();
            if (url.equals(potencial_file.getString(Files.URL))) {
                res = new Files(potencial_file.getString(Files.EMAIL), potencial_file.getString(Files.NAME),potencial_file.getString(Files.URL), Double.parseDouble(potencial_file.getString(Files.WEIGHT)), potencial_file.getString(Files.TYPE));
            }
        }

        return res;
    }
}
