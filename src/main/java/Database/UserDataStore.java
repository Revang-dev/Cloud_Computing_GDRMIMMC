package Database;

import Entity.Users;

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

    public void addUser(Users user){
        IncompleteKey key = keyFactory.newKey();
        FullEntity<IncompleteKey> userData = Entity.newBuilder(key)
                .set("mail",user.getEmail())
                .set("level",user.getLevel())
                .set("point",user.getPoint())
                .build();
        Entity newUser = datastore.add(userData);
    }
}
