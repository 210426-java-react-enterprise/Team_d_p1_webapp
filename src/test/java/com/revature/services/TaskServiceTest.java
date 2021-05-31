package com.revature.services;


import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.DateFormatException;
import com.revature.exceptions.MessageLengthOutOfBoundsException;
import com.revature.exceptions.TitleLengthOutOFBoundsException;
import com.revature.statements.StatementBuilder;
import com.revature.statements.StatementType;
import com.revature.util.ORMState;
import com.revature.util.ResultSetDTO;
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
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;


/** 
* TaskService Tester. 
* 
* @author Nicholas Reicno
* @since <pre>May 16, 2021</pre> 
* @version 1.0 
*/ 
public class TaskServiceTest {

    @InjectMocks
    TaskService sut;


    @Mock
    ResultSetDTO mockResultSetDTO;

    @Mock
    StatementType mockStatementType;

    @Mock
    ResultSet rs;

    @Mock
    StatementBuilder mockStatementBuilder;

@Before
public void before() throws Exception { openMocks(this); }

@After
public void after() throws Exception {
    sut = null;
    mockResultSetDTO = null;
    rs = null;
    mockStatementBuilder = null;
    mockStatementType = null;
} 

@Test (expected = MessageLengthOutOfBoundsException.class)
    public void testMessageLengthValidation() throws SQLException, ImproperConfigurationException {
    Task task = new Task();
    task.setTaskId(500);
    String message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris viverra varius sem, nec accumsan risus pretium sit amet. Nulla laoreet risus vel quam tristique porta. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per massa nunc. Words";
    sut.updateTaskContent(task.getTaskId(), message);
}

@Test (expected = TitleLengthOutOFBoundsException.class)
    public void testTitleLengthOutOfBounds() throws SQLException, ImproperConfigurationException {
    Task task = new Task();
    task.setTaskId(500);
    String title = "Very long super long dude so long title man this is so long its the longest title ever.";
    sut.updateTaskTitle(task.getTaskId(), title);
}

@Test (expected = DateFormatException.class)
    public void testDateOutOfFormatException() throws SQLException, ImproperConfigurationException {
    Task task = new Task();
    task.setTaskId(500);
    String date = "113/223/29";
    sut.updateTaskDueDate(task.getTaskId(), date);
}

@Test
    public void validTaskMessageUpdate() {
    Task task = new Task();
    task.setTaskId(500);
    task.setTaskMessage("This is a perfectly valid message");
    try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
        mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
        when(ORMState.getStatementBuilder("update")).thenReturn(mockStatementBuilder);
        when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
        when(mockStatementType.UPDATE.createStatementWithCondition(any())).thenReturn(rs);
        when(mockResultSetDTO.resultSetForSingleTask(any())).thenReturn(task);
        assertTrue(sut.updateTaskContent(task.getTaskId(), task.getTaskMessage()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Test
    public void validTaskTitleUpdate() {
    Task task = new Task();
    task.setTaskId(500);
    task.setTaskTitle("perfectly valid title");
    try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
        mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
        when(ORMState.getStatementBuilder("update")).thenReturn(mockStatementBuilder);
        when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
        when(mockStatementType.UPDATE.createStatementWithCondition(any())).thenReturn(rs);
        when(mockResultSetDTO.resultSetForSingleTask(any())).thenReturn(task);
        assertTrue(sut.updateTaskContent(task.getTaskId(), task.getTaskTitle()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Test
    public void validTaskDueDateUpdate() {
    Task task = new Task();
    task.setTaskId(500);
    task.setDateDue("1/11/2010");
    try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
        mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
        when(ORMState.getStatementBuilder("update")).thenReturn(mockStatementBuilder);
        when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
        when(mockStatementType.UPDATE.createStatementWithCondition(any())).thenReturn(rs);
        when(mockResultSetDTO.resultSetForSingleTask(any())).thenReturn(task);
        assertTrue(sut.updateTaskContent(task.getTaskId(), task.getDateDue()));
    } catch (Exception e) {
        e.printStackTrace();
    }
}

@Test
    public void validTaskState() {
    Task task = new Task();
    task.setTaskId(500);
    try (MockedStatic<ORMState> mockORMstate = Mockito.mockStatic(ORMState.class)) {
        mockORMstate.when((MockedStatic.Verification) ORMState.getStatementBuilder(any())).thenReturn(mockStatementBuilder);
        when(ORMState.getStatementBuilder("update")).thenReturn(mockStatementBuilder);
        when(mockStatementBuilder.buildStatement(any())).thenReturn(rs);
        when(mockStatementType.UPDATE.createStatementWithCondition(any())).thenReturn(rs);
        when(mockResultSetDTO.resultSetForSingleTask(any())).thenReturn(task);
        assertTrue(sut.updateTaskState(task.getTaskId()));
    } catch (Exception e) {
        e.printStackTrace();
    }

}

@Test
    public void testMessageLengthError() throws SQLException, ImproperConfigurationException {
        Task task = new Task();
        task.setTaskId(500);
        String message = " Test phrase";
        when(mockStatementBuilder.buildStatement(any(),any())).thenThrow(new SQLException());
        sut.updateTaskContent(task.getTaskId(), message);
    }

@Test
    public void taskLengthError() throws SQLException, ImproperConfigurationException {
        Task task = new Task();
        task.setTaskId(500);
        String title = "Test phrase";
        when(mockStatementBuilder.buildStatement(any(),any())).thenThrow(new SQLException());
        sut.updateTaskTitle(task.getTaskId(), title);

}

}

