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

public class CreationUserRank extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();

    

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
            String level = "Noob";
            if(jsontest.get("level") != null ){
                    level = jsontest.get("level").getAsString();
            }
            String mdp = jsontest.get("mdp").getAsString();
            Users newAccount = new Users(username, mdp, level);
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
        System.out.print("______momomomo_____");
        ArrayList<Users> list = userManager.getAllUser();
        System.out.print(list);
        for(Users usr : list){
            out.println("----------------------------------------------------\n");
            out.println(usr.toString()+"\n");
            out.println("----------------------------------------------------\n");
        }
    }


}
