package servlet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.User;
import org.json.JSONException;
import stockage.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateAcount extends HttpServlet {
    UserManager userManager = new UserManager();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
            String username = jsontest.get("username").getAsString();
            String accountlevel = jsontest.get("accountlevel").getAsString();
            String email = jsontest.get("email").getAsString();
            if(accountlevel.equalsIgnoreCase("bronze") || accountlevel.equalsIgnoreCase("silver") || accountlevel.equalsIgnoreCase("gold")){
                User newAccount = new User(username, accountlevel, email);
                if(userManager.getUser(username) == null){
                    userManager.createUser(newAccount);
                    out.println("L utilisateur " + username + " a ete cree avec succes !");
                }
                else{
                    out.println("Nom d'utilisateur deja utilise !");
                }
            }
            else{
                out.println("Niveau de compte de l utilisteur inccorect !");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }


}
