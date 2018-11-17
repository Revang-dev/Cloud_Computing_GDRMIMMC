package servlet;

import Database.DownloadCloudStore;
import Database.FileDataStore;
import Database.UploadCloudStore;
import Database.UserDataStore;
import Entity.Files;
import Entity.Users;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import utils.EmptyFileGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class Upload extends HttpServlet {
    FileDataStore fileManager = FileDataStore.getInstance();
    UploadCloudStore cloud = new UploadCloudStore();
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Upload”,
     Body: {
        userID: Bob@gmail.com
        videoPath: “Bob/myVideos/”
        name: "video5.mp4"
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
            String name = jsontest.get("name").getAsString();
            String fileURL = jsontest.get("fileURL").getAsString();
            String email = jsontest.get("email").getAsString();
            double taille = jsontest.get("taille").getAsDouble();
            byte[] file = java.nio.file.Files.readAllBytes(Paths.get(fileURL));
            String type = jsontest.get("type").getAsString();
            String trueUrl = fileURL + cloud.uploadFile(name,file);
            
            Users user = UserDataStore.getUser(email);
            int time = (int) System.currentTimeMillis();
            if (user != null) {
            	switch(user.getLevel()) {
            	
            	case "Noob":
            		if (time - user.getReq(0) > 60000) {
            			user.setReq(0, time);
            			fileManager.addFile(new Files(email, name, trueUrl, taille, type));
            		}
            		else {
            			// send mail: lol noob
            		}
            	
            	case "Casual":
            		for (int i = 0; i < 2; i++) {
            			if (time - user.getReq(i) > 60000) {
                			user.setReq(i, time);
                			fileManager.addFile(new Files(email, name, trueUrl, taille, type));
                			break;
                		}
            		}
            	
            	case "Leet":
            		for (int i = 0; i < 4; i++) {
            			if (time - user.getReq(i) > 60000) {
                			user.setReq(i, time);
                			fileManager.addFile(new Files(email, name, trueUrl, taille, type));
                			break;
                		}
            		}
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }
}
