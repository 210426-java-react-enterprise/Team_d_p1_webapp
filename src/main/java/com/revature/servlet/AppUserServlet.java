package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.AppUser;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameTakenException;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
import com.revature.services.AppUserService;
import com.revature.util.AppState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class AppUserServlet extends HttpServlet {

    private final AppUserService appUserService = AppState.getInstance().getAppUserService();

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
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        int age = Integer.parseInt(req.getParameter("age"));

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
//
//        // 2.) Service execution
        try {
            AppUser registeredUser = appUserService.registerUser(user);
            resp.getWriter().println("User added to Database: \n" + registeredUser);
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
        } catch (SQLException | ImproperConfigurationException throwables) {
            throwables.printStackTrace();
        }

        // 3.) Persist to database


        // 4.) Send info back to client

        //Return the status code
        // 100 -
        // 200 -
        // 300 - Redirect
        // 400 - Client Side Error
        // 500 - Server Side Error

    }


    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("put servlet fired");
        InputStream json = req.getInputStream();

        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);
        System.out.println(jsonMap);

        AppUser user = null;
        try {
            user = appUserService.loginUser(jsonMap.get("username").toString(), jsonMap.get("password").toString());
        } catch (UserNotFoundException | SQLException | ImproperConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        if(user.getUserID() == 0) {
            resp.getWriter().println("Please check your credentials");
        } else {

            resp.getWriter().println("Succesfully Logged In: \n" + user.getUsername());


        }



    }
}
