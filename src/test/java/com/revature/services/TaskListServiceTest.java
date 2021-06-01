package com.revature.services;

import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.InvalidEntryException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.statements.StatementBuilder;
import com.revature.statements.StatementType;
import com.revature.util.*;
import com.revature.util.datasource.ConnectionFactory;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.openMocks;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedList;

import static org.mockito.MockitoAnnotations.openMocks;

/**
 * TaskListService Tester.
 *
 * @author Nicholas Recino
 * @version 1.0
 * @since <pre>May 16, 2021</pre>
 */
public class TaskListServiceTest {
    @InjectMocks
    TaskListService sut;

    @Mock
    ResultSetDTO mockResultSetDTO;

    @Mock
    Task mockTask;

    @Mock
    StatementType mockStatementType;

    @Mock
    StatementBuilder mockStatementBuilder;

    @Mock
    AppUserService appUserService;

    @Mock
    ResultSet rs;

    @Before
    public void before() throws Exception {
        ConnectionFactory.setConnection("task-force.c2iiztx3t7wq.us-east-1.rds.amazonaws.com", "postgres", "revature", "test");
        openMocks(this);
    }

    @After
    public void after() {
        sut = null;
        mockResultSetDTO = null;
        rs = null;
        mockStatementBuilder = null;
        mockStatementType = null;
        appUserService = null;
    }


    /**
     * Method: addTask(Task newTask)
     */
    @Test
    public void testIsValidAddTask() throws Exception {
        AppUser userToTest = new AppUser();
        mockTask = new Task();
        mockTask.setTaskId(2);
        mockTask.setTaskTitle("test");
        mockTask.setTaskState(true);
        mockTask.setTaskMessage("test message");
        mockTask.setDateDue("05/30/21");
        mockTask.setUserId(1);
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("insert")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.INSERT.createStatementWithCondition(any())).thenReturn(rs);
            assertTrue(sut.addTask(mockTask));

        }

    }

    @Test
    public void testNullAddTask() throws Exception {
        mockTask = new Task();
        mockTask.setTaskId(1);
        mockTask.setTaskTitle("test");
        mockTask.setTaskState(true);
        mockTask.setTaskMessage("test message");
        mockTask.setDateDue("05/30/21");
        mockTask.setUserId(1);
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("insert")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.INSERT.createStatementWithCondition(any())).thenReturn(rs);
            assertFalse(sut.addTask(null));

        }

    }

////Need to throw SQL Exception to get to catch block.
//    @Test
//    public void testEmptyAddTask() throws Exception {
//        mockTask = new Task();
//        mockTask.setTaskId(-4);
//
//        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
//            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
//            when(ORMState.getStatementBuilder("insert")).thenReturn(mockStatementBuilder);
//            when(mockStatementBuilder.buildStatement(anyString())).thenReturn(rs);
//            when(mockStatementType.INSERT.createStatementWithCondition(anyString())).thenReturn(rs);
//            boolean test = sut.addTask(mockTask);
//            assertFalse(test);
//        }
//
//    }

    /**
     * Method: removeTask()
     */
    @Test
    public void testRemoveTaskByValidTaskId() throws Exception {
        mockTask = new Task();
        mockTask.setTaskId(1);
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("delete")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(anyString())).thenReturn(rs);
            when(mockStatementType.DELETE.createStatementWithCondition(anyString())).thenReturn(rs);
            assertTrue(sut.removeTask(mockTask.getTaskId()));

        }
    }


    @Test
    public void testRemoveTaskByInvalidTaskId() throws Exception {
        mockTask = new Task();
        mockTask.setTaskId(-5);

        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("delete")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(anyString())).thenReturn(rs);
            when(mockStatementType.DELETE.createStatementWithCondition(anyString())).thenReturn(rs);
            assertFalse(sut.removeTask(mockTask.getTaskId()));
        }
    }


    @Test
    public void testGetAllTasksByUsername() throws ImproperConfigurationException, Exception {
        Task userTask = new Task();
        userTask.setUserId(5);
        AppUser goodUser = mock(AppUser.class);
        goodUser.setUserID(userTask.getUserId());
        LinkedList<HashMap> listToReturn = new LinkedList<>();
        for(int i = 0; i<4;i++){
            HashMap<String,String> testMap = new HashMap<>();
            testMap.put("task_state","true");
            listToReturn.add(testMap);
        }
        HashMap<String,String> testMap = new HashMap<>();
        testMap.put("task_state","false");
        listToReturn.add(testMap);


        when(appUserService.findUserByUsername("username")).thenReturn(goodUser);
        when(mockResultSetDTO.resultSetToLinkedListTask(any())).thenReturn(listToReturn);
        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(appUserService.findUserByUsername(any())).thenReturn(goodUser);
            LinkedList<HashMap> returnedList = sut.getAllTasksByUsername("username");
            assertEquals(returnedList.getFirst().get("task_state"),"false");
        }catch(Exception e){
            e.printStackTrace();
        }






    }

    /**
     * Method: getAllUncompletedTasks()
     */
    @Test
    public void testGetAllUncompletedTasks() throws ImproperConfigurationException, SQLException, ResourceNotFoundException {
        Task task1 = new Task();
        task1.setTaskState(false);

        try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
            mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
            when(ORMState.getStatementBuilder("select")).thenReturn(mockStatementBuilder);
            when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
            when(mockStatementType.SELECT.createStatementWithCondition(any())).thenReturn(rs);


            //Assert that the userID of one of the hasmaps is equal to the one recieved.

            LinkedList<HashMap> returnedValue = sut.getAllUncompletedTasks();
            assertNotEquals(returnedValue, rs);
//            Assert.assertSame(returnedValue, rs);

        } catch (Exception e) {
            e.printStackTrace();


        }

    }
}



