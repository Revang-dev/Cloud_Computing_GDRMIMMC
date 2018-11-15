package Entity;

import java.text.DateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

public class Files {

    private String emailUtilisateur;
    private String url;
    private double weight;
    private String dateCreation;
    private String type;
    private int id;
    private String statut;
    //[START keys]
    public static final String EMAIL = "userEmail";
    public static final String URL = "url";
    public static final String WEIGHT = "weight";
    public static final String DATE = "creationDate";
    public static final String TYPE = "type";
    public static final String ID = "id";
    public static final String STATUT = "statut";
    // [END keys]
    
    public Files(String newEmail, String newUrl, double newWeight, String newType){
        this.emailUtilisateur = newEmail;
        this.url = newUrl;
        this.weight = newWeight;
        this.type = newType;
        this.id = 0;
        this.statut = "Alive";
        this.dateCreation = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                DateFormat.SHORT).format(new Date());
    }

    //GETTER//
    public String getEmailUtilisateur(){
        return this.emailUtilisateur;
    }

    public String getUrl(){
        return this.url;
    }

    public double getWeight(){
        return this.weight;
    }

    public String getDateCreation(){
        return this.dateCreation;
    }

    public String getType(){
        return this.type;
    }

    public int getId(){
        return this.id;
    }

    public String getStatut(){
        return this.statut;
    }

    //SETTER//
    public void setEmailUtilisateur(String data){
        this.emailUtilisateur = data;
    }

    public void setUrl(String data){
        this.url = data;
    }

    public void setWeight(double data){
        this.weight = data;
    }

    public void setDateCreation(String data){
        this.dateCreation = data;
    }

    public void setType(String data){
        this.type = data;
    }

    public void setId(int data){
        this.id = data;
    }

    public void setStatut(String data){
        this.statut = data;
    }

    //FUNCTION//

    public void deleteFile(){
        this.statut = "ToDelete";
    }

    public String toString(){
        String res = "FILE DATA : \n";
        res += "Poster : " + this.emailUtilisateur + "\n";
        res += "Url : " + this.url + "\n";
        res += "Weight : " + this.weight + "\n";
        res += "Date Creation : " + this.dateCreation + "\n";
        res += "Type : " + this.type + "\n";
        res += "ID : " + this.id + "\n";
        res += "Statut : " + this.statut + "\n";
        return res;
    }
}
