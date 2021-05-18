package com.revature.servlet;

import com.revature.entities.Task;
import com.revature.services.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class TaskListServlet extends HttpServlet {

    private TaskService taskService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

        String title = req.getParameter("title");
        String message = req.getParameter("content");
        String taskState = req.getParameter("state");
        String dateDue = req.getParameter("dueDate");

        Task newTask = new Task();
    }
}
