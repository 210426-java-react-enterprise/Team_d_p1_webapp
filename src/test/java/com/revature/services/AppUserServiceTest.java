package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameTakenException;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.SQLException;

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
    }

    @Test(expected = InvalidUsernameException.class)
    public void testRegisterUserWithInvalidName() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("two");
        userToTest.setPassword("9letters");
        userToTest.setEmail("email@test.com");
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
    public void testRegisterUserWithAvailableUsername() throws UsernameTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("Available");
        assertTrue(sut.isUsernameAvailable(userToTest.getUsername()));
    }

    @Test (expected = UsernameTakenException.class)
    public void testRegisterUserWithTakenUsername() throws UsernameTakenException, SQLException, ImproperConfigurationException {
        // Test case will fail

        AppUser userToTest = new AppUser();
        userToTest.setUsername("taken");
        sut.isUsernameAvailable(userToTest.getUsername());
    }

    @Test
    public void testRegisterUserWithAvailableEmail() throws EmailTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        userToTest.setEmail("TestEmail@email.com");
        assertTrue(sut.isEmailAvailable(userToTest.getEmail()));
    }

    @Test (expected = EmailTakenException.class)
    public void testRegisterUserWithTakenEmail() throws EmailTakenException, SQLException, ImproperConfigurationException {
        // Test case will fail at this moment

        AppUser userToTest = new AppUser();
        userToTest.setUsername("TakenEmail@email.com");
        sut.isEmailAvailable(userToTest.getEmail());
    }


    /**
    *
    * Method: loginUser()
    *
    */
    @Test
    public void testLoginUser() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("TestUser");
        userToTest.setPassword("Password12");
        AppUser loginUserTest = sut.loginUser(userToTest.getUsername(), userToTest.getPassword());
        assertNotNull(loginUserTest);
    }

    @Test (expected = UserNotFoundException.class)
    public void testLoginNullCredentials() throws UserNotFoundException, SQLException, ImproperConfigurationException {
        // Test Will fail
        AppUser userToTest = new AppUser();
        sut.loginUser(userToTest.getUsername(), userToTest.getPassword());
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
