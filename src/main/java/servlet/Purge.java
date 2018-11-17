package servlet;

import Database.FileDataStore;
import Database.CloudStore;
import Database.UserDataStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Purge extends HttpServlet{
    FileDataStore fileManager = FileDataStore.getInstance();
    CloudStore cloud = new CloudStore();
    UserDataStore userStore = UserDataStore.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        cloud.deleteAll(fileManager.getAllFile());
        fileManager.deleteAll();
        userStore.deleteAll();
    }

}