package servlet;

import Database.UserDataStore;
import Entity.Users;
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

public class QeueNoobPull extends HttpServlet{
    UserDataStore userStore = UserDataStore.getInstance();

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


            String email = jsontest.get("mail").getAsString();
            String fileName = jsontest.get("fileName").getAsString();

            Queue queue = QueueFactory.getQueue("pull-queue");
            String content = email + "/" + fileName;
            out.print("ok 1");
            queue.add(TaskOptions.Builder.withMethod(TaskOptions.Method.PULL)
                    .payload(content)
                    .tag(email));
            out.print("ok 2");
            List<TaskHandle> tasks = queue.leaseTasksByTag(300, TimeUnit.SECONDS, 1, email);
            out.print("ok 3");
            String message = processTasks(tasks, queue);
            out.println(message);
            out.print("ok 4");
        }catch (Exception e) {
            e.printStackTrace();
            out.println(e.toString());
        }
    }


    private String processTasks(List<TaskHandle> tasks, Queue q) throws UnsupportedEncodingException {
        for (TaskHandle task : tasks) {
            String payload = new String(task.getPayload());
            String[] array = payload.split("/");
            Users user = userStore.getUserbyMail(array[0]);
            MailSender.SendLinkTo(user.getEmail(), "yesss sa marche");
            q.deleteTask(task);
        }
        return "sa marche je crois";
    }


}
