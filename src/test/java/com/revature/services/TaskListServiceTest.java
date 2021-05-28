package com.revature.services;

import com.revature.util.ResultSetService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
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
    ResultSetService mockResultSetService;

@Before
public void before() throws Exception {
    openMocks(this);
} 

@After
public void after() throws Exception {
    sut = null;
    mockResultSetService = null;
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


} 
