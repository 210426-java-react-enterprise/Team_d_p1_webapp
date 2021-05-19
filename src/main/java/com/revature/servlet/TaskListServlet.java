package com.revature.servlet;

import com.revature.entities.Task;
import com.revature.services.TaskListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TaskListServlet extends HttpServlet {

    private TaskListService taskListService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        String title = req.getParameter("title");
        String message = req.getParameter("message");
        String taskState = req.getParameter("taskState");
        String dateDue = req.getParameter("dueDate");

        Task newTask = new Task(dateDue, title, message, taskState);

        taskListService.addTask(newTask);

        // TODO persist to database

        resp.setStatus(202);

        resp.getWriter().print("Hello out there. Your user has been created");

    }


}
