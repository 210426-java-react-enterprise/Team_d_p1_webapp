package com.revature.services;

import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.DateFormatException;
import com.revature.exceptions.MessageLengthOutOfBoundsException;
import com.revature.exceptions.TitleLengthOutOFBoundsException;
import com.revature.util.ResultSetDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.SQLException;

import static org.junit.Assert.*;
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

@Before
public void before() throws Exception { openMocks(this); }

@After
public void after() throws Exception {
    sut = null;
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


} 
