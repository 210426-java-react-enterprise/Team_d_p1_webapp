package com.revature.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppUserServlet extends HttpServlet {

    //Ties into AppUserService here not present on this branch at this moment, will tie in when this is pushed

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

       // 1.) Get the information
        /*
            post /uri http/1.1
            <headers>

            content-type:

            username =
            password =
            email =
            first_name =
            last_name =
            birthdate =

         */
        // Register User
        // gather information out of request
        // construct an AppUser
        // send information back to Client
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        // 2.) Service execution


        // 3.) Persist to database


        // 4.) Send info back to client

        //Return the status code
        // 100 -
        // 200 -
        // 300 - Redirect
        // 400 - Client Side Error
        // 500 - Server Side Error
        resp.setStatus(202);
        resp.getWriter().println("The user has been created " + username);

    }
}
