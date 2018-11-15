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


    public void updateUser(Users user){
        Key key = keyFactory.newKey(user.getId());
        Entity entity = Entity.newBuilder(key)
                .set(Users.EMAIL, user.getEmail())
                .set(Users.PASSWORD, user.getPassword())
                .set(Users.LEVEL, user.getLevel())
                .set(Users.POINT, user.getPoint())
                .build();
        datastore.update(entity);
    }

    public void addUser(Users user){
        IncompleteKey key = keyFactory.newKey();
        FullEntity<IncompleteKey> userData = Entity.newBuilder(key)
                .set("mail",user.getEmail())
                .set("level",user.getLevel())
                .set("point",user.getPoint())
                .set("password",user.getPassword())
                .build();
        Entity newUser = datastore.add(userData);
    }
}
