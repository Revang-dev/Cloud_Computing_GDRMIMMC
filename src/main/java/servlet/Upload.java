package servlet;

import Database.FileDataStore;
import Entity.Files;
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

public class Upload extends HttpServlet {
    FileDataStore fileManager = FileDataStore.getInstance();
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Upload”,
     Body: {
        userID: Bob@gmail.com
        videoPath: “Bob/myVideos/video5.mp4”
        videoSize: 34
        dateRequest: timeNow()
        }
    }
    */
    /////////////////////////////////////////////////////////////
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }

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
            String fileURL = jsontest.get("fileURL").getAsString();
            String email = jsontest.get("email").getAsString();
            Double taille = jsontest.get("taille").getAsDouble();
            String type = jsontest.get("type").getAsString();
            fileManager.addFile(new Files(email, fileURL, taille, type));
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }
}
