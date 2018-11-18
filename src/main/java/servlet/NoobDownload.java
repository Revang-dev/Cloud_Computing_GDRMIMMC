package servlet;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoobDownload extends HttpServlet{

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("mail");
        String fileName = req.getParameter("fileName");
        Queue queue = QueueFactory.getQueue("casualleet");
        queue.add(TaskOptions.Builder.withUrl("/noobworker")
                                    .param("email",email)
                                    .param("fileName",fileName));
    }
}
