package Entity;

public class Users {
    private String email;
    private int point;
    private String level;

    public  Users(String newEmail){
        this.email = newEmail;
        this.level = "Noob";
        this.point = 0;
    }

    //GETTER//
    public String getEmail(){
        return this.email;
    }

    public String getLevel(){
        return this.level;
    }

    public int getPoint(){
        return this.point;
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
}
