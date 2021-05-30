package com.revature.services;

import com.revature.util.ResultSetDTO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.MockitoAnnotations.openMocks;

/** 
* TaskListService Tester. 
* 
* @author Nicholas Recino
* @since <pre>May 16, 2021</pre> 
* @version 1.0 
*/ 
public class TaskListServiceTest {
    @InjectMocks
    TaskListService sut;

    @Mock
    ResultSetDTO mockResultSetDTO;

@Before
public void before() throws Exception {
    openMocks(this);
} 

@After
public void after() throws Exception {
    sut = null;
    mockResultSetDTO = null;
} 

/** 
* 
* Method: addTask() 
* 
*/ 
@Test
public void testAddTask() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: removeTask() 
* 
*/ 
@Test
public void testRemoveTask() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: generateTaskReport() 
* 
*/ 
@Test
public void testGenerateTaskReport() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: generateTaskReportByDate(LocalDateTime byDateTime) 
* 
*/ 
@Test
public void testGenerateTaskReportByDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updatePublicView() 
* 
*/ 
@Test
public void testUpdatePublicView() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTasksCompletedOnTime() 
* 
*/ 
@Test
public void testGetTasksCompletedOnTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTasksNotCompletedInTime() 
* 
*/ 
@Test
public void testGetTasksNotCompletedInTime() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUncompletedTasks() 
* 
*/ 
@Test
public void testGetUncompletedTasks() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: createTaskList() 
* 
*/ 
@Test
public void testCreateTaskList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setTaskListComplete() 
* 
*/ 
@Test
public void testSetTaskListComplete() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateTaskListToDB() 
* 
*/ 
@Test
public void testUpdateTaskListToDB() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTaskListFromDB() 
* 
*/ 
@Test
public void testGetTaskListFromDB() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTaskListsByUser() 
* 
*/ 
@Test
public void testGetTaskListsByUser() throws Exception { 
//TODO: Test goes here... 
} 


} 
