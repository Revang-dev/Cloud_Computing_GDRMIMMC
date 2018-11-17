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
        UserDataStore userStore = UserDataStore.getInstance();
        //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Upload”,
     Body: {
        userID: Bob@gmail.com
        filePath: “Bob/myVideos/”
        name: "video5.mp4"
        videoSize: 34.0
        type: video
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
            EmptyFileGenerator gen = new EmptyFileGenerator();
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
                if (jsontest.get("Action").getAsString().equals("Upload")) {
                    JsonObject body = (JsonObject) jsontest.get("Body");
                    String name = body.get("name").getAsString();
                    String fileURL = body.get("filePath").getAsString();
                    String email = body.get("userID").getAsString();
                    double taille = body.get("videoSize").getAsDouble();
                    // byte[] file = java.nio.file.Files.readAllBytes(littefile);
                    byte[] file = gen.CreateLocalFile(5);
                    String type = body.get("type").getAsString();
                    Users user = userStore.getUserbyMail(email);

                    if (user != null) {
                        long time = System.currentTimeMillis();
                        String[] tab_req = user.getReq().split(",");
                        switch (user.getLevel()) {

                            case "Noob" :
                                out.println("NOOB case");
                                if (time - Long.parseLong(tab_req[0]) > 60000) {
                                    tab_req[0] = "" + time;
                                    user.setReq(tab_req[0] + ",0,0,0");
                                    String trueUrl = "" + cloud.uploadFile(name, file);
                                    fileManager.addFile(new Files(email, name, trueUrl, taille, type));
                                    out.println("FILE POSTED ON :" + trueUrl);
                                    userStore.updateUser(user);
                                    break;
                                } else {
                                    out.println("NOOOOOOOB");
                                    break;
                                }

                            case "Casual":
                                out.println("CASUAL case");
                                for (int i = 0; i < 2; i++) {
                                    if (time - Long.parseLong(tab_req[i]) > 60000) {
                                        tab_req[i] = "" + time;
                                        user.setReq(tab_req[0] + "," + tab_req[1] + ",0,0");
                                        String trueUrl = "" + cloud.uploadFile(name, file);
                                        fileManager.addFile(new Files(email, name, trueUrl, taille, type));
                                        out.println("FILE POSTED ON :" + trueUrl);
                                        userStore.updateUser(user);
                                        break;
                                    }
                                }

                            case "Leet":
                                out.println("LEET case");
                                for (int i = 0; i < 4; i++) {
                                    if (time - Long.parseLong(tab_req[i]) > 60000) {
                                        tab_req[i] = "" + time;
                                        user.setReq(tab_req[0] + "," + tab_req[1] + "," + tab_req[2] + "," + tab_req[3]);
                                        String trueUrl = "" + cloud.uploadFile(name, file);
                                        fileManager.addFile(new Files(email, name, trueUrl, taille, type));
                                        out.println("FILE POSTED ON :" + trueUrl);
                                        userStore.updateUser(user);
                                        break;
                                    }
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
