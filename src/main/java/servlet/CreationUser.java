package servlet;

import Database.UserDataStore;
import Entity.Users;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CreationUser extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “addUser”,
        Body: {
            userID: Bob@gmail.com
        }
    }
    {Action: “deleteUser”,
        Body: {
            userID: Bob@gmail.com
        }
    }
    */
    /////////////////////////////////////////////////////////////

   /* @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // Set response content type
        resp.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + "Yo" + "</h1>");
    }*/

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
            String username = jsontest.get("username").getAsString();
            //String level = jsontest.get("level").getAsString();
            String mdp = jsontest.get("mdp").getAsString();
            Users newAccount = new Users(username, mdp);
            userManager.addUser(newAccount);
            out.println(newAccount.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        ArrayList<Users> list = userManager.getAllUser();
        for(Users usr : list){
            out.println("----------------------------------------------------\n");
            out.println(usr+"\n");
            out.println("----------------------------------------------------\n");
        }
    }


}
