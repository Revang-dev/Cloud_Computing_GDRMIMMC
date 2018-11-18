package servlet;

import Database.FileDataStore;
import Database.UserDataStore;
import Entity.Users;
import Entity.permissionUpload;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mail.MailSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class NoobDownload extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();
    FileDataStore fileManager = FileDataStore.getInstance();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) { //report an error
        }

        try {
            JsonParser jparser = new JsonParser();
            JsonElement obj = jparser.parse(jb.toString());
            JsonObject jsontest = obj.getAsJsonObject();
            if (jsontest.get("Action").getAsString().equals("download")) {
                Queue resultat;
                JsonObject body = (JsonObject) jsontest.get("Body");
                String email = body.get("mail").getAsString();
                String fileName = body.get("fileName").getAsString();
                out.println("here");
                //NOT WORKING. CANNOT UPLOAD
                /*resultat = QueueFactory.getQueue("noob");
                Queue queue = QueueFactory.getDefaultQueue();
                resultat.add(TaskOptions.Builder.withUrl("/noobworker")
                        .param("email", email)
                        .param("fileName", fileName));*/
                Users user = userManager.getUserbyMail(email);
                String[] tab_req = user.getReq().split(",");
                permissionUpload permission = new permissionUpload(user.getLevel());
                if(permission.canSendRequest(tab_req)) {
                    String trueUrl = fileManager.getFileByName(fileName).getUrl();
                    MailSender.SendLinkTo(user.getEmail(), trueUrl);
                }else{
                    out.println("lol non, vous devez attendre 1 min avant de lancer votre prochaine requete d'upload");
                    MailSender.SendLinkTo(user.getEmail(), "lol non, vous devez attendre 1 min avant de lancer votre prochaine requete d'upload");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }

}