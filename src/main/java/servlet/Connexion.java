package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.com.google.gson.JsonObject;
import com.google.cloud.datastore.*;

import Database.UserDataStore;
import Entity.Users;
import utils.JsonGenerator;

import java.io.IOException;

public class Connexion extends HttpServlet {
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Connexion”,
     Body: {
        userID: Bob@gmail.com
        pass: "mot_de_passe"
        }
    }
    */
    /////////////////////////////////////////////////////////////

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	JsonGenerator.getInstance();
    	UserDataStore.getInstance();
    	JsonObject json = JsonGenerator.requestToJson(req);
    	Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    	Users user = null;
    		
    	if (json.get("Action").toString().equals("Connexion")) {
    		JsonObject body = (JsonObject) json.get("Body");
    		String email = body.get("userID").toString();
    		String pass = body.get("pass").toString();
    		user = UserDataStore.getUser(email, pass);
    	}
    }
}
