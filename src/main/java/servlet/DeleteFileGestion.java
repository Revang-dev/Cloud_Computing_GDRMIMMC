package servlet;

import Database.CloudStore;
import Database.FileDataStore;
import Database.UserDataStore;
import Entity.Files;
import Entity.Users;
import com.google.api.Http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DeleteFileGestion extends HttpServlet{

    FileDataStore fileStore = FileDataStore.getInstance();
    UserDataStore userStore = UserDataStore.getInstance();
    CloudStore deletor = new CloudStore();


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        ArrayList<Files> fileList = fileStore.getAllFile();
        long time = System.currentTimeMillis();
        for(Files file : fileList){
            long date = file.getDateCreation();
            Users user = userStore.getUserbyMail(file.getEmailUtilisateur());
            switch (user.getLevel()) {
                case "Noob" :
                    out.println("NOOB case");
                    if ((time - date) > 300000) {
                        deletor.deleteFile(file.getName());
                        break;
                    }
                case "Casual" :
                    out.println("CASUAL case");
                    if ((time - date) > 600000) {
                        deletor.deleteFile(file.getName());
                        break;
                    }
                case "Leet" :
                    out.println("LEET case");
                    if ((time - date) > 1800000) {
                        deletor.deleteFile(file.getName());
                        break;
                    }
            }
        }
    }
}
