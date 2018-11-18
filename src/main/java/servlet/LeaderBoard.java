package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Database.UserDataStore;
import Entity.Users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
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
    	List<Users> users = dataStore.getAllUser();
    	users.sort(new Comparator<Users>() {

			@Override
			public int compare(Users o1, Users o2) {
				return -Long.compare(o1.getPoint(), o2.getPoint());
			}
		});
		if(users.size() >= 10){
			users = users.subList(0,10);
		}else{
			users = users.subList(0,users.size());
		}


		PrintWriter out = resp.getWriter();
		for(Users usr : users){
			out.println("--------\n");
			out.println(usr.toString()+"\n");
			out.println("--------\n");
		}

    	
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

    }
}
