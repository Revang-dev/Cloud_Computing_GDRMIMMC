package utils;

import com.google.appengine.repackaged.com.google.gson.JsonObject;

public class JsonGenerator {
    static private JsonGenerator instance;

    private JsonGenerator() {}

    static public JsonGenerator getInstance() {
        if (instance == null) {
            instance = new JsonGenerator();
        }
        return instance;
    }

    static public JsonObject getConnexionJson(String userID, String pass) {
    	JsonObject json_body = new JsonObject();
    	JsonObject json = new JsonObject();
        json_body.addProperty("userID", userID);
        json_body.addProperty("pass", pass);
        json.addProperty("Action", "Connexion");
        json.add("Body", json_body);
        return json;
    }

    public static void main (String[] args) {
    	getInstance();
    	JsonObject test = getConnexionJson("Bob@gmail.com", "mdp");
    	System.out.println(test.toString());
    }
}
