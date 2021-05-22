package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.Task;
import com.revature.services.TaskListService;
import com.revature.util.AppState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class TaskListServlet extends HttpServlet {

    private final TaskListService taskListService = AppState.getTaskListService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // Task state now boolean will only be adjusted from true/false on backend.  Set to true upon creation - everett
        String title = req.getParameter("title");
        String message = req.getParameter("message");
        String dateDue = req.getParameter("dueDate");

        Task newTask = new Task(dateDue, title, message);

        taskListService.addTask(newTask);

        // TODO persist to database

        resp.setStatus(202);

        resp.getWriter().print("Task has been created");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        InputStream json = req.getInputStream();

        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

        String taskIdString = jsonMap.get("taskId").toString();
        String title = jsonMap.get("title").toString();
        String message = jsonMap.get("message").toString();
        String dueDate = jsonMap.get("dueDate").toString();

        int taskId = Integer.parseInt(taskIdString);

        Task task = new Task(dueDate, title, message, taskId);

        taskListService.updateTask(task);


        resp.getWriter().println("Task has been updated: \n" + task.toString());

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream json = req.getInputStream();

        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

        String taskIdString = jsonMap.get("taskId").toString();
        int taskId = Integer.parseInt(taskIdString);

        taskListService.removeTask(taskId);

        resp.getWriter().println("Task has been deleted");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream json = req.getInputStream();

        Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

        String username = jsonMap.get("username").toString();

        taskListService.getAllTasksByUsername(username);

        resp.getWriter().println("All tasks for: " + username);
    }
}
