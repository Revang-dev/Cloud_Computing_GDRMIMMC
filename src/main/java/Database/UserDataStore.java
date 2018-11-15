package Database;

import com.google.cloud.datastore.*;


public class UserDataStore {
    // Mail,Point,Rank
    private Datastore datastore;
    private KeyFactory keyFactory;

    public UserDataStore(){
        this.datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory = datastore.newKeyFactory().setKind("User2");
    }

    public void updateUser(){

    }

    public void addUser(String mail){
        IncompleteKey key = keyFactory.newKey();
        FullEntity<IncompleteKey> userData = Entity.newBuilder(key)
                .set("mail",mail)
                .set("level", 0)
                .set("point", 0)
                .build();
        Entity newUser = datastore.add(userData);
    }
}
