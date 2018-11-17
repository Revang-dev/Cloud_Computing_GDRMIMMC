package Entity;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.datastore.Value;

public class Users {
    private String email;
    private long point;
    private String level;
    private String password;
    private ArrayList<Integer> req;
    //[START keys]
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String LEVEL = "level";
    public static final String POINT = "point";
    // [END keys]

    public  Users(String newEmail,String mdp){
        this.email = newEmail;
        this.level = "Noob";
        this.point = 0;
        this.password = mdp;
        this.req = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
        	this.req.add((int) (System.currentTimeMillis() - 60000)); // 60000 = 1 minute
        }
    }

    public  Users(String newEmail,String mdp, String level){
        this.email = newEmail;
        this.level = "Noob";
        this.point = 0;
        this.password = mdp;
        this.level = level;
        this.req = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
        	this.req.add((int) (System.currentTimeMillis() - 60000)); // 60000 = 1 minute
        }
    }
    
    @SuppressWarnings("unchecked")
	public  Users(String newEmail,String mdp, long point, String level, List<Value<?>> req){
        this.email = newEmail;
        this.level = level;
        this.point = point;
        this.password = mdp;
        this.req = (ArrayList<Integer>) (ArrayList<?>) req;
    }

    //GETTER//
    public String getEmail(){
        return this.email;
    }

    public String getLevel(){
        return this.level;
    }

    public long getPoint(){
        return this.point;
    }

    public String getPassword(){
        return this.password;
    }
    
    public long getReq(int i) {
		return req.get(i);
	}

    //SETTER//
    public void setEmail(String newEmail){
        this.email = newEmail;
    }

    public void setLevel(String thisLevel){
        this.level = thisLevel;
    }

    public void setPoint(int nbr){
        this.point = nbr;
    }

    public void setPassword(String mdp){
        this.password = mdp;
    }
    
    public void setReq(int indice, int value) {
		this.req.add(indice, value);
	}
    

    //FUNCTION//
    public void addPoint(int nbr){
        this.point += nbr;
    }

    public void levelUp(){
        if(this.level == "Noob"){
            this.level = "Casual";
        }
        else if (this.level == "Casual") {
            this.level = "Leet";
        }
    }

    @Override
    public String toString(){
        return "email: "+email+", point:"+point+", level:"+level;
    }

}
