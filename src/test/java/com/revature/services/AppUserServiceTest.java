package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
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
    * Method: registerUser()
    *
    */
    @Test
    public void testRegisterValidUser() throws Exception {
    //TODO: Test goes here...
        AppUser userToTest = new AppUser();
        userToTest.setUsername("9letters");
        userToTest.setPassword("9letters");
        userToTest.setEmail("email@test.com");

        assertTrue(sut.registerUser(userToTest));
    }

    @Test(expected = InvalidUsernameException.class)
    public void testRegisterUserWithInvalidName() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("two");
        userToTest.setPassword("9letters");
        userToTest.setEmail("email@test.com");

        assertTrue(sut.registerUser(userToTest));
    }

    @Test(expected = InvalidPasswordException.class)
    public void testRegisterUserWithInvalidPassword() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("9Letters");
        userToTest.setPassword("two");
        userToTest.setEmail("email@test.com");

        sut.registerUser(userToTest);
    }

    @Test(expected = InvalidEmailException.class)
    public void testRegisterUserWithInvalidEmail() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("9Letters");
        userToTest.setPassword("9Letters");
        userToTest.setEmail("emailtest.com");

        sut.registerUser(userToTest);
    }

    @Test
    public void testRegisterUserWithTakenUsernameOrEmail(){

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
