package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameTakenException;
import com.revature.exceptions.invalid.InvalidEmailException;
import com.revature.exceptions.invalid.InvalidPasswordException;
import com.revature.exceptions.invalid.InvalidUsernameException;
import com.revature.statements.StatementBuilder;
import com.revature.statements.StatementType;
import com.revature.util.ORMState;
import com.revature.util.ResultSetDTO;
import com.revature.util.datasource.ConnectionFactory;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.Mockito.when;

/** 
* AppUserService Tester. 
* 
* @author Nichols Recino
* @since <pre>May 16, 2021</pre> 
* @version 1.0 
*/ 
public class AppUserServiceTest {

    @InjectMocks
    AppUserService sut;

    @Mock
    ResultSetDTO mockResultSetDTO;

    @Mock
    StatementType mockStatementType;

    @Mock
    ResultSet rs;

    @Mock
    StatementBuilder mockStatementBuilder;

    @Before
    public void before() throws Exception{
        ConnectionFactory.setConnection("task-force.c2iiztx3t7wq.us-east-1.rds.amazonaws.com","postgres","revature","test");
        openMocks(this);
} 

    @After
    public void after(){
    sut = null;
    mockResultSetDTO = null;
    rs = null;
    mockStatementBuilder = null;
    mockStatementType = null;
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



    /**
    *
    * Method: registerUser()
    *
    */

    @Test(expected = InvalidUsernameException.class)
    public void testRegisterUserWithInvalidUsername() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("two");
        sut.registerUser(userToTest);

    }

    @Test(expected = InvalidPasswordException.class)
    public void testRegisterUserWithInvalidPassword() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("9Letters");
        userToTest.setPassword("two");
        sut.registerUser(userToTest);
    }

    @Test
    public void testIsUsernameAvailableTrue() throws UsernameTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            assertTrue(sut.isUsernameAvailable(userToTest.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsUsernameAvailableFalse() throws UsernameTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("takenUsername");
        userToTest.setPassword("password1!");
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            assertFalse(sut.isUsernameAvailable(userToTest.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsEmailAvailableTrue() throws EmailTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            assertTrue(sut.isEmailAvailable(userToTest.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testIsEmailAvailableFalse() throws EmailTakenException, SQLException, ImproperConfigurationException {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("Available");
        userToTest.setPassword("password1!");
        userToTest.setEmail("availableEmail@email.com");
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            assertFalse(sut.isEmailAvailable(userToTest.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
    *
    * Method: loginUser()
    *
    */
    @Test (expected = UserNotFoundException.class)
    public void testLoginUserUsernameNotFound() throws Exception {
        AppUser userToTest = new AppUser();
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(null);
            sut.loginUser(userToTest.getUsername(), userToTest.getPassword());
        }
    }

    @Test
    public void testLoginUser() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("registeredUser");
        userToTest.setPassword("password1!");
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            sut.loginUser(userToTest.getUsername(), userToTest.getPassword());
        }
    }

    /**
     * finduserbyusername
     */

    @Test
    public void testFindUserByUsername() throws Exception{
        AppUser userToTest = new AppUser();
        userToTest.setUsername("registeredUser");
        userToTest.setPassword("password1!");
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            assertEquals(userToTest, sut.findUserByUsername("registeredUser"));
        }
    }


    /**
     * registerUser
     */

    @Test (expected = UsernameTakenException.class)
    public void testRegisterUserUsernameTaken() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("registeredUser");
        userToTest.setPassword("password1!");
        userToTest.setEmail("testEmail@email.com");
        userToTest.setFirstName("test");
        userToTest.setLastName("user");
        userToTest.setAge(33);
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);
            when(mockResultSetDTO.resultSetToUser(any())).thenReturn(userToTest);
            sut.registerUser(userToTest);
        }

    }

    @Test (expected = InvalidUsernameException.class)
    public void registerUserInvalidUsername() throws Exception{
        AppUser userToTest = new AppUser();
        userToTest.setUsername("reg");
        sut.registerUser(userToTest);
    }

    @Test (expected = InvalidPasswordException.class)
    public void registerUserInvalidPassword() throws SQLException, EmailTakenException, ImproperConfigurationException, InvalidPasswordException, InvalidUsernameException, UsernameTakenException, InvalidEmailException {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("ValidUsername");
        userToTest.setPassword("pass");
        sut.registerUser(userToTest);
    }

    @Test (expected = InvalidEmailException.class)
    public void registerUserInvalidEmail() throws Exception {
        AppUser userToTest = new AppUser();
        userToTest.setUsername("ValidUsername");
        userToTest.setPassword("password!1");
        userToTest.setEmail("invalidemail");
        sut.registerUser(userToTest);
    }


} 
