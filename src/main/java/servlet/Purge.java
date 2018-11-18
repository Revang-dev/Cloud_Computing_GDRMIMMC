package servlet;

import Database.FileDataStore;
import Database.CloudStore;
import Database.UserDataStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Purge extends HttpServlet{
    FileDataStore fileManager = FileDataStore.getInstance();
    CloudStore cloud = new CloudStore();
    UserDataStore userStore = UserDataStore.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();
        cloud.deleteAll(fileManager.getAllFile());
        out.print("All files deleted from Cloud store");
        fileManager.deleteAll();
        out.print("All files deleted from Data store");
        userStore.deleteAll();
        out.print("All users deleted from Data store");
    }

}
