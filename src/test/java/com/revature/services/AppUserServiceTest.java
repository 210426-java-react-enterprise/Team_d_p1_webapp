package com.revature.services;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.*;

/** 
* AppUserService Tester. 
* 
* @author Nichols Recino
* @since <pre>May 16, 2021</pre> 
* @version 1.0 
*/ 
public class AppUserServiceTest {
    AppUserService sut;

    @Before
    public void before(){
    sut = new AppUserService();
} 

    @After
    public void after(){
    sut = null;
} 

    /**
    *
    * Method: verify(String username, String password)
    *
    */
    @Test
    public void testVerify() throws Exception {
//TODO: Test goes here... 
} 

    /**
    *
    * Method: validatePotentialUserInfo(String password, String username, String email)
    *
    */
    @Test
    public void testValidatePotentialUserInfo() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: registerUser()
    *
    */
    @Test
    public void testRegisterUser() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: loginUser()
    *
    */
    @Test
    public void testLoginUser() throws Exception {
    //TODO: Test goes here...
    }


    /**
    *
    * Method: isValidUsername(String username)
    *
    */
    @Test
    public void testIsValidUsernameWithValidInfo(){
        String validUsername = "9letters";
        assertTrue(sut.isValidUsername(validUsername));
    }
    @Test
    public void testIsValidUsernameWithInvalidInfo(){
        String invalidUsername = "fails";
        assertFalse(sut.isValidUsername(invalidUsername));
    }
    @Test
    public void testIsValidUsernameWithNull(){
        assertFalse(sut.isValidUsername(null));
    }

    /**
    *
    * Method: isValidPassword(String password)
    *
    */
    @Test
    public void testIsValidPassword(){
        String validPassword = "9letters";
        assertTrue(sut.isValidPassword(validPassword));
    }
    @Test
    public void testIsInvalidPassword(){
        String invalidPassword = "fails";
        assertFalse(sut.isValidPassword(invalidPassword));
    }
    @Test
    public void testIsValidPasswordWithNull(){
        assertFalse(sut.isValidPassword(null));
    }

    /**
    *
    * Method: isValidEmail(String email)
    *
    */
    @Test
    public void testIsValidEmail(){
        String validEmail = "test@email.com";
        assertTrue(sut.isValidEmail(validEmail));
    }
    @Test
    public void testIsInvalidEmail(){
        String invalidEmail = "test";
        assertFalse(sut.isValidEmail(invalidEmail));
    }
    @Test
    public void testIsValidEmailWithNull(){
        assertFalse(sut.isValidEmail(null));
    }


} 
