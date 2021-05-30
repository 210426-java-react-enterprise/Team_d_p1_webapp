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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        InputStream json = req.getInputStream();
        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

        String username = jsonMap.get("username").toString();
        String password = jsonMap.get("password").toString();
        String email = jsonMap.get("email").toString();
        String firstName = jsonMap.get("first_name").toString();
        String lastName = jsonMap.get("last_name").toString();
        String ageString = jsonMap.get("age").toString();
        int age = Integer.parseInt(ageString);

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

        InputStream json = req.getInputStream();

        HttpSession session = req.getSession();

        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

        AppUser user = null;
        try {
            user = appUserService.loginUser(jsonMap.get("username").toString(), jsonMap.get("password").toString());
        } catch (UserNotFoundException | SQLException | ImproperConfigurationException e) {
            e.printStackTrace();
        }
        System.out.println(user);
        if(user.getUserID() == 0){
            resp.getWriter().println("Please check your credentials");
        } else {

            resp.getWriter().println("Successfully Logged In: \n" + user.getUsername());
            session.setAttribute("this-user",user);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();

        }
    }
}
