package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Connexion extends HttpServlet {
    //////////////////////__JSON__///////////////////////////////
    /*
    {Action: “Connexion”,
     Body: {
        userID: Bob@gmail.com
        pass: "mot_de_passe"
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
