package servlet;

import Database.FileDataStore;
import Database.UserDataStore;
import Entity.Users;
import Entity.permissionUpload;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
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
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CasualLeetPull extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();
    FileDataStore fileManager = FileDataStore.getInstance();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            /*
            Queue queue = QueueFactory.getQueue("pull-queue");
            String content = email + "/" + fileName;

            queue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL)
                    .payload(content)
                    .tag(email));

            List<TaskHandle> tasks = queue.leaseTasksByTag(300, TimeUnit.SECONDS, 1, email);

            String message = processTasks(tasks, queue);
            out.println(message); */

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

                Users user = userManager.getUserbyMail(email);
                String[] tab_req = user.getReq().split(",");
                permissionUpload permission = new permissionUpload(user.getLevel());
                if(permission.canSendRequest(tab_req)) {
                    String trueUrl = fileManager.getFileByName(fileName).getUrl();
                    out.println("un mail de téléchargement a été envoyé :"+trueUrl);
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


    private String processTasks(List<TaskHandle> tasks, Queue q) throws UnsupportedEncodingException {
        for (TaskHandle task : tasks) {
            String payload = new String(task.getPayload());
            String[] array = payload.split("/");
            Users user = userManager.getUserbyMail(array[0]);
            MailSender.SendLinkTo(user.getEmail(), "sa marche");
            q.deleteTask(task);
        }
        return "sa marche je crois";
    }


}
