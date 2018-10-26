package entities;

public class User {
    private String username;
    private String accountLevel;
    private String email;
    private int point;

    public User(String username, String accountLevel, String email){
        this.username = username;
        this.accountLevel = accountLevel;
        point = 0;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountLevel() {
        return accountLevel;
    }

    public void setAccountLevel(String accountLevel) {
        this.accountLevel = accountLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int list){
        this.point = list;
    }

}
