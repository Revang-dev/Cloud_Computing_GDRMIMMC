package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.*;

import Database.UserDataStore;
import Entity.Users;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import utils.JsonGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


public class Connexion extends HttpServlet {
	UserDataStore userManager = UserDataStore.getInstance();
	JsonGenerator jsonConvert = JsonGenerator.getInstance();
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
		PrintWriter out = resp.getWriter();
		StringBuffer jb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jb.append(line);
			}
		} catch (Exception e) { /*report an error*/ }

		try {
			JsonParser jparser = new JsonParser();
			JsonElement obj = jparser.parse(jb.toString());
			JsonObject jsontest = obj.getAsJsonObject();
			Users user = null;
			if (jsontest.get("Action").getAsString().equals("Connexion")) {
				JsonObject body = (JsonObject) jsontest.get("Body");
				String email = body.get("userID").getAsString();
				String pass = body.get("pass").getAsString();
				user = UserDataStore.getUser(email, pass);
				out.println("The connection " + user.toString() + " is successful");
			}

		}
		catch (Exception e) {
			e.printStackTrace();
			out.println(e.toString());
		}
    }
}
