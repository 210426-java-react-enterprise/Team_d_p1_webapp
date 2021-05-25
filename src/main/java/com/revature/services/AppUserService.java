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
    private final Pattern emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
            "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)" +
            "\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
            "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private final ResultSetService resultSetService;

    public AppUserService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }

//    TODO needs logic to lookup information that we obtain from database, most likely will pair to a session cache that gets created, different task and different branch though
    private boolean verify(String username,String password){
        return true;
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
            throw new InvalidUsernameException("Please Enter Valid Username");
        }
        if(!isValidPassword(userToBeRegistered.getPassword())){
            throw new InvalidPasswordException("Please enter valid password");
        }
        if(!isValidEmail(userToBeRegistered.getEmail())){
            throw new InvalidEmailException("Please Enter valid email");
        }
        if(!isUsernameAvailable(userToBeRegistered.getUsername())){
            throw new UsernameTakenException("Username is taken");
        }
        if(!isEmailAvailable(userToBeRegistered.getEmail())){
            throw new EmailTakenException("Email is taken");
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

        return validatedUser != null;
    }

    public boolean isEmailAvailable(String email) throws SQLException, ImproperConfigurationException {

        AppUser user = new AppUser();
        user.setEmail(email);

        AppUser validatedUser = resultSetService.resultSetToUser(StatementType.SELECT.createStatementWithCondition(user, "email"));

        return validatedUser != null;
    }

}
