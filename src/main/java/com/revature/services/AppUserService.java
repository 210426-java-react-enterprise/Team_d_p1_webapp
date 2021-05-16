package com.revature.services;

import java.util.regex.Pattern;

public class AppUserService {
    private final Pattern userNamePattern = Pattern.compile("[A-Za-z0-9_]+");
    private final Pattern passwordPattern = Pattern.compile("[A-Za-z0-9_!@#$%&*]+");
    private final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

//    TODO
    public void verify(String username,String password){

    }

    private boolean isValidUsername(String username){
        if(username.length()<7)
            return false;
        return(userNamePattern.matcher(username).matches());
}

    private boolean isValidPassword(String password){
        if(password.length()<7)
            return false;
        return(passwordPattern.matcher(password).matches());
    }

    private boolean isValidEmail(String email) {
        if(email.length()<6)
            return false;
        return(emailPattern.matcher(email).matches());
    }

//    TODO
    public void validatePotentialUserInfo(String password, String username,String email){

    }

//    TODO
    public void registerUser(){

    }

//    TODO
    public void loginUser(){

    }

}
