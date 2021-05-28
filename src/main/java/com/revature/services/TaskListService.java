package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.entities.Task;
import com.revature.entities.TaskList;
import com.revature.exception.ImproperConfigurationException;
import com.revature.statements.StatementType;
import com.revature.util.AppState;
import com.revature.util.ResultSetService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class TaskListService {
    private TaskList taskList;
    private Task newTask;
    private Task task;
    private AppUser user;
    private final ResultSetService resultSetService;
    private final AppUserService appUserService = AppState.getInstance().getAppUserService();


    public TaskListService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }


    //    TODO create database call to ORM to persist task
    public void addTask(Task newTask) {
        try {

            Task returnsTask = resultSetService.resultSetForSingleTask(StatementType.INSERT.createStatementWithCondition(newTask, "user_id"));

            System.out.println(returnsTask);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hypothetical task has been added: " + newTask.toString());
    }

    //    TODO create db call to remove task from task table by ID
    public void removeTask(int taskId) {
        try {
            task.setTaskId(taskId);

            Task removeTask = resultSetService.resultSetForSingleTask(StatementType.DELETE.createStatementWithCondition(task, "task_id"));

            System.out.println(removeTask);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Task has been deleted");
    }

    // TODO create db call that gets all tasks by username
    public LinkedList<HashMap> getAllTasksByUsername(String username) throws ImproperConfigurationException, SQLException {

        AppUser user = appUserService.findUserByUsername(username);
        int userId = user.getUserID();
        Task task = new Task();
        task.setUserId(userId);

        LinkedList<HashMap> tasks = resultSetService.resultSetToLinkedListTask(StatementType.SELECT.createStatementWithCondition(task, "user_id"));

        return tasks;

    }

}