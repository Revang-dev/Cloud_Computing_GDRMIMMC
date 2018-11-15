package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UserDataStore;
import Entity.Users;

import java.io.IOException;
import java.util.List;

public class LeaderBoard extends HttpServlet {
    //////////////////////__JSON__///////////////////////////////
    //    /*
    //    {Action: “leaderBoard”}
    //    */
    //    /////////////////////////////////////////////////////////////
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
    	UserDataStore dataStore = UserDataStore.getInstance();
    	List<Users> users;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }
}
