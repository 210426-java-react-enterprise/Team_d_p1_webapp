package com.revature.services;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.*;

/** 
* TaskService Tester. 
* 
* @author Nicholas Reicno
* @since <pre>May 16, 2021</pre> 
* @version 1.0 
*/ 
public class TaskServiceTest {
    TaskService sut;

@Before
public void before() throws Exception {
    sut = new TaskService();
} 

@After
public void after() throws Exception {
    sut = null;
} 

/** 
* 
* Method: updateTaskContent(String newContent) 
* 
*/ 
@Test
public void testUpdateTaskContent() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addTaskContent(String additionalContent) 
* 
*/ 
@Test
public void testAddTaskContent() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateTaskDueDate(LocalDateTime newDueDate) 
* 
*/ 
@Test
public void testUpdateTaskDueDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: updateTaskTitle(String newTitle) 
* 
*/ 
@Test
public void testUpdateTaskTitle() throws Exception { 
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
* Method: addTaskToTaskList() 
* 
*/ 
@Test
public void testAddTaskToTaskList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: saveTaskToDB() 
* 
*/ 
@Test
public void testSaveTaskToDB() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTaskFromDB() 
* 
*/ 
@Test
public void testGetTaskFromDB() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: taskCompleted() 
* 
*/ 
@Test
public void testTaskCompleted() throws Exception { 
//TODO: Test goes here... 
} 


} 
