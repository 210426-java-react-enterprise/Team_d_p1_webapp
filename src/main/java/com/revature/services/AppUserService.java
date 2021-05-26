package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.*;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetService;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AppUserService {
    private final Pattern userNamePattern = Pattern.compile("[A-Za-z0-9_]+");
    private final Pattern passwordPattern = Pattern.compile("[A-Za-z0-9_!@#$%&*]+");
    private final Pattern emailPattern = Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
    private final ResultSetService resultSetService;

    public AppUserService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }

    public boolean isValidUsername(String username){
        if(username == null || username.length()<7)
            return false;
        return(userNamePattern.matcher(username).matches());
}

    public boolean isValidPassword(String password){
        if(password == null || password.length()<7)
            return false;
        return(passwordPattern.matcher(password).matches());
    }

    public boolean isValidEmail(String email) {
        if(email == null || email.length()<6)
            return false;
        return(emailPattern.matcher(email).matches());
    }

    public AppUser registerUser(AppUser userToBeRegistered) throws InvalidUsernameException, InvalidEmailException, InvalidPasswordException, UsernameTakenException, EmailTakenException, SQLException, ImproperConfigurationException {
        if(!isValidUsername(userToBeRegistered.getUsername())){
            throw new InvalidUsernameException("\nPlease Enter Valid Username");
        }
        if(!isValidPassword(userToBeRegistered.getPassword())){
            throw new InvalidPasswordException("\nPlease enter valid password");
        }
        if(!isValidEmail(userToBeRegistered.getEmail())){
            throw new InvalidEmailException("\nPlease Enter valid email");
        }
        if(!isUsernameAvailable(userToBeRegistered.getUsername())){
            throw new UsernameTakenException("\nUsername is taken");
        }
        if(!isEmailAvailable(userToBeRegistered.getEmail())){
            throw new EmailTakenException("\nEmail is taken");
        }

        return resultSetService.resultSetToUser(StatementType.INSERT.createStatement(userToBeRegistered));
    }


    public AppUser loginUser(String username, String password) throws UserNotFoundException, SQLException, ImproperConfigurationException {

        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(password);

        AppUser loggedInUser = resultSetService.resultSetToUser(StatementType.SELECT.createStatementWithCondition(user, "username", "password"));
        if(loggedInUser == null) {
            throw new UserNotFoundException("Username not found, please check credentials and try again");
        }

        return loggedInUser;

    }

    public boolean isUsernameAvailable(String username) throws SQLException, ImproperConfigurationException {

        AppUser user = new AppUser();
        user.setUsername(username);

        AppUser validatedUser = resultSetService.resultSetToUser(StatementType.SELECT.createStatementWithCondition(user, "username"));
        System.out.println("validated user rs:" + validatedUser);

        return validatedUser.getPassword() == null;
    }

    public boolean isEmailAvailable(String email) throws SQLException, ImproperConfigurationException {

        AppUser user = new AppUser();
        user.setEmail(email);

        AppUser validatedUser = resultSetService.resultSetToUser(StatementType.SELECT.createStatementWithCondition(user, "email"));

        return validatedUser.getPassword() == null;
    }

    public AppUser findUserByUsername(String username) {

        AppUser user = new AppUser();
        AppUser resultUser = new AppUser();
        user.setUsername(username);

        try {
            resultUser = resultSetService.resultSetToUser(StatementType.SELECT.createStatementWithCondition(user, "username"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultUser;

    }
}
