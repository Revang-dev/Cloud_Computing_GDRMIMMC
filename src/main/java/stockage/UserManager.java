package stockage;

import com.google.cloud.datastore.*;
import entities.User;

import java.util.ArrayList;

public class UserManager {
    private final Datastore datastore;
    private final KeyFactory keyFactory;

    public UserManager(){
        datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory = datastore.newKeyFactory().setKind("user");
    }

    public User getUser(String username){
        User res = null;
        EntityQuery query =
                Query.newEntityQueryBuilder().setKind("user")
                        .build();
        QueryResults<Entity> results = datastore.run(query);
        while (results.hasNext()) {
            Entity entity = results.next();
            if(entity.getString("username").equalsIgnoreCase(username)){
                String level = entity.getString("accountlevel");
                String email = entity.getString("email");
                String currentVid = String.valueOf(entity.getLong("currentVid"));
                res = new User(username, level, email);
                res.setPoint(Integer.valueOf(currentVid));
            }

        }
        return res;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> res = new ArrayList<>();
        EntityQuery query =
                Query.newEntityQueryBuilder().setKind("user")
                        .build();
        QueryResults<Entity> results = datastore.run(query);
        while (results.hasNext()) {
            Entity entity = results.next();
            String username = entity.getString("username");
            String accountlevel = entity.getString("accountlevel");
            String currentVid = String.valueOf(entity.getLong("currentVid"));
            String email = entity.getString("email");
            User user = new User(username, accountlevel, email);
            user.setPoint(Integer.valueOf(currentVid));
            res.add(user);
        }
        return res;
    }

    public void updateUser(User user){
        EntityQuery query =
                Query.newEntityQueryBuilder()
                        .setKind("user")
                        .setFilter(StructuredQuery.PropertyFilter.eq("username", user.getUsername()))
                        .build();
        QueryResults<Entity> results = datastore.run(query);
        if (!results.hasNext()){
            return;
        }
        Entity.Builder builder = Entity.newBuilder(results.next());
        builder.set("username",user.getUsername());
        builder.set("accountlevel", user.getAccountLevel());
        builder.set("email", user.getEmail());
        builder.set("currentVid", user.getPoint());
        Entity entity = builder.build();
        datastore.update(entity);
    }

    public void createUser(User u) {
        IncompleteKey key = keyFactory.newKey();
        FullEntity<IncompleteKey> user = Entity.newBuilder(key)
                .set("username", u.getUsername())
                .set("email", u.getEmail())
                .set("accountlevel", u.getAccountLevel())
                .set("currentVid", u.getPoint())
                .build();
        datastore.add(user);
    }

}
