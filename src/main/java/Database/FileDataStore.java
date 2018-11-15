package Database;

import Entity.Files;
import utils.JsonGenerator;

import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.cloud.datastore.*;


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


    public static Files getFile(int id){
        Files res = null;
        Entity potencial_file;

        EntityQuery datastore_query = Query.newEntityQueryBuilder()
                .setKind("File2")
                .build();
        QueryResults<Entity> datastore_files = datastore.run(datastore_query);

        while (datastore_files.hasNext()) {
            potencial_file = datastore_files.next();
            if (Integer.toString(id).equals(potencial_file.getString(Files.ID))) {
                res = new Files(potencial_file.getString(Files.EMAIL), potencial_file.getString(Files.URL), Double.parseDouble(potencial_file.getString(Files.WEIGHT)), potencial_file.getString(Files.TYPE));
            }
        }

        return res;
    }
}
