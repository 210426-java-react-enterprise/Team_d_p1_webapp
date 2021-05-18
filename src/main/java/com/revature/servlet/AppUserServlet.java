package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.AppUser;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameTakenException;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
import com.revature.services.AppUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AppUserServlet extends HttpServlet {
    private AppUserService service;

    //Ties into AppUserService here not present on this branch at this moment, will tie in when this is pushed
    public AppUserServlet(AppUserService service){
        this.service = service;
    }

    public AppUserServlet(){

    }

    // get READ
    // put UPDATE
    // post CREATE
    // delete DELETE
    // patch special type of UPDATE
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
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        int age = Integer.parseInt(req.getParameter("age"));

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        //  TODO Age either change to int ignore or do logic to determine the date

        // 2.) Service execution
        try {
            service.registerUser(user);
        } catch (InvalidUsernameException e) {
            resp.setStatus(400);
            resp.getWriter().println("Invalid Username Specified!!!");
        } catch (InvalidEmailException e) {
            resp.setStatus(400);
            resp.getWriter().println("Invalid Email Specified!!!");
        } catch (InvalidPasswordException e) {
            resp.setStatus(400);
            resp.getWriter().println("Invalid Password specified");
        } catch (UsernameTakenException e) {
            resp.setStatus(400);
            resp.getWriter().println("Username already taken!!!");
        } catch (EmailTakenException e) {
            resp.setStatus(400);
            resp.getWriter().println("Email already taken!!!");
        }

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

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        /*
        JSON form for authenticate
        {
        "username":"",
        "password":""
        }
         */
        Map<String, Object> jsonMap = new ObjectMapper().readValue(req.getInputStream(),HashMap.class);
       AppUser user = new AppUser();
        try {
            user = service.loginUser(jsonMap.get("username").toString(),jsonMap.get("password").toString());
        } catch (UserNotFoundException e) {
            resp.setStatus(400);
            resp.getWriter().println("User Not Found, Please Register for an account or check your credentials!!!");
        }
        
        resp.setStatus(202);
        resp.getWriter().println("User Found, Logging In " + user.getUsername());
    }

    // Sets up a redirect call and a  forward in order
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("https://www.google.com");
        req.getRequestDispatcher("/someOtherResource").forward(req,resp);
    }
}
