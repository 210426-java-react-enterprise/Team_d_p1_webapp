package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.entities.AppUser;
import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.services.AppUserService;
import com.revature.services.TaskListService;
import com.revature.services.TaskService;
import com.revature.util.AppState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class TaskListServlet extends HttpServlet {

    private final TaskListService taskListService = AppState.getTaskListService();
    private final TaskService taskService = AppState.getInstance().getTaskService();
    private final AppUserService appUserService = AppState.getInstance().getAppUserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        // Task state now boolean will only be adjusted from true/false on backend.  Set to true upon creation - everett

        try {
            String title = req.getParameter("title");
            String message = req.getParameter("message");
            String dateDue = req.getParameter("dueDate");
            String username = req.getParameter("username");
            AppUser user = appUserService.findUserByUsername(username);

            Task newTask = new Task(dateDue, title, message, user.getUserID());

            taskListService.addTask(newTask);



            resp.setStatus(202);

            resp.getWriter().print("Task has been created");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter writer = resp.getWriter();

        try {
            InputStream json = req.getInputStream();
            Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

            // Get task ID for task service
            String taskIdString = jsonMap.get("taskId").toString();
            int taskId = Integer.parseInt(taskIdString);

            /*
                Get Update Option for task update:
                - title
                - content
                - dueDate
                - state
             */

            String updateOption = jsonMap.get("option").toString();

            switch (updateOption) {
                case "title": {
                    String title = jsonMap.get("title").toString();
                    taskService.updateTaskTitle(taskId, title);
                        writer.println("Title for task #" + taskId + " has been updated");
                        break;

//                    writer.println("Something went wrong.");
//                    break;


                }
                case "content": {
                    String message = jsonMap.get("message").toString();
                    taskService.updateTaskContent(taskId, message);
                    writer.println("Content for task #" + taskId + " has been updated");
                    break;
                }
                case "date_due": {
                    String dueDate = jsonMap.get("date_due").toString();
                    taskService.updateTaskDueDate(taskId, dueDate);
                    writer.println("Due Date for task #" + taskId + " has been updated");
                    break;
                }
                case "state": {
                    taskService.updateTaskState(taskId);
                    writer.println("The completed state for task #" + taskId + " has been updated");
                    break;

                }
                default: {
                    writer.println("Please enter valid input!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            InputStream json = req.getInputStream();

            Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

            String taskIdString = jsonMap.get("taskId").toString();
            int taskId = Integer.parseInt(taskIdString);

            taskListService.removeTask(taskId);

            resp.getWriter().println("Task has been deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            LinkedList<HashMap> tasks;
            HttpSession session = req.getSession(false);
            AppUser requestingUser = (session == null) ? null : (AppUser) session.getAttribute("this-user");
            if (requestingUser == null) {
                resp.setStatus(401);
                return;
            } else if (requestingUser.getUsername().equals("admin")) {
                tasks = taskListService.getAllUncompletedTasks();
                session.setAttribute("uncompleted_tasks",tasks);
                return;
            }else{
                InputStream json = req.getInputStream();

                Map<String, Object> jsonMap = new ObjectMapper().readValue(json, HashMap.class);

                String username = jsonMap.get("username").toString();
                tasks = taskListService.getAllTasksByUsername(username);
            }

            String taskJSONString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(tasks);

            System.out.println(taskJSONString);

            resp.getWriter().println(taskJSONString);

        } catch (ImproperConfigurationException e) {
            resp.setStatus(500);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
