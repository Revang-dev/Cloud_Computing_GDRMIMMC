package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreationUser extends HttpServlet{
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

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    }

}
