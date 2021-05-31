package com.revature.services;

import com.revature.util.ResultSetService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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
    ResultSetService mockResultSetService;


    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {
        sut = null;
    }




    /**
     * Method: updateTaskContent(int taskId, String newContent)
     */
    @Test
    public void testUpdateTaskContent() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: updateTaskTitle(int taskId, String newTitle)
     */

    @Test
    public void testUpdateTaskTitle() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: updateDueDate(int taskId, String newTitle)
     */

    @Test
    public void testUpdateDueDate() throws Exception {
        //TODO: Test goes here...
    }


    /**
     * Method: updateTaskState(int taskId)
     */


    @Test
    public void testUpdateTaskState() throws Exception {
        //TODO: Test goes here...

    }
}


