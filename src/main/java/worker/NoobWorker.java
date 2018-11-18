package worker;

import Database.UserDataStore;
import Entity.Users;
import Entity.permissionUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NoobWorker extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();


    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String fileName = req.getParameter("name");
        Users user = userManager.getUserbyMail(email);
        String[] tab_req = user.getReq().split(",");
        permissionUpload permission = new permissionUpload(user.getLevel());
        if(permission.canSendRequest(tab_req)){
            out.println("Can do it");
        }else{
            out.println("can't do it");
        }
    }
}
