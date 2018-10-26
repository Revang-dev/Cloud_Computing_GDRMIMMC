package entities;


public class Fichier {
    private String name;
    private String length;

    public String getLength() {
        return length;
    }

    public void setLength(String content) {
        this.length = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




    public Fichier(String name, String length){
        this.length = length;
        this.name = name;
    }
}
