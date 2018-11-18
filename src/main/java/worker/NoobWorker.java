package worker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.CloudStore;
import Database.FileDataStore;
import Database.UserDataStore;
import Entity.Files;
import Entity.Users;
import Entity.permissionUpload;
import mail.MailSender;


import java.io.IOException;
import java.io.PrintWriter;

public class NoobWorker extends HttpServlet{
    UserDataStore userManager = UserDataStore.getInstance();
    FileDataStore fileManager = FileDataStore.getInstance();
    CloudStore storeManager = new CloudStore();

    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String fileName = req.getParameter("fileName");
        Users user = userManager.getUserbyMail(email);
        String[] tab_req = user.getReq().split(",");
        permissionUpload permission = new permissionUpload(user.getLevel());
        if(permission.canSendRequest(tab_req)) {
            Files file = fileManager.getFileByName(fileName);
            String trueUrl = fileManager.getFileByName(fileName).getUrl();
            out.println("un mail de téléchargement a été envoyé :"+trueUrl);
            MailSender.SendLinkTo(user.getEmail(), trueUrl);

            //storeManager.downloadFile(user,file);
        }else{
            out.println("lol non, vous devez attendre 1 min avant de lancer votre prochaine requete de download");
            MailSender.SendLinkTo(user.getEmail(), "lol non, vous devez attendre 1 min avant de lancer votre prochaine requete download");
        }
    }
}
