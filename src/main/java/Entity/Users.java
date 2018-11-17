package Entity;

public class Users {
    private String email;
    private long point;
    private String level;
    private String password;
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
    }
    
    public  Users(String newEmail,String mdp, long point, String level){
        this.email = newEmail;
        this.level = level;
        this.point = point;
        this.password = mdp;
    }


    public  Users(String newEmail,String mdp, String level){
        this.email = newEmail;
        this.level = "Noob";
        this.point = 0;
        this.password = mdp;
        this.level = level;
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
