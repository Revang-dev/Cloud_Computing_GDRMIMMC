package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Upload extends HttpServlet {
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

    }
}
