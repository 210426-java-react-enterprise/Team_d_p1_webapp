package com.revature.services;

import com.revature.entities.AppUser;
import com.revature.entities.Task;
import com.revature.entities.TaskList;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.ResourceNotFoundException;
import com.revature.statements.StatementType;
import com.revature.util.AppState;
import com.revature.util.ResultSetDTO;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskListService {
    private TaskList taskList;
    private Task newTask;
    private Task task;
    private AppUser user;
    private final ResultSetDTO resultSetDTO;
    private final AppUserService appUserService = AppState.getInstance().getAppUserService();


    public TaskListService(ResultSetDTO resultSetDTO) {
        this.resultSetDTO = resultSetDTO;
    }


    //    TODO create database call to ORM to persist task
    public boolean addTask(Task newTask) {
        if (newTask == null || newTask.getTaskMessage().trim().isEmpty()) {
            return false;
            //throw new InvalidEntryException("Invalid entry, please try again.");


        } else if (newTask != null) {
            try {
                Task returnsTask = resultSetDTO.resultSetForSingleTask(StatementType.INSERT.createStatementWithCondition(newTask, "user_id"));

                System.out.println(returnsTask);
                System.out.println("Hypothetical task has been added: " + newTask.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    //    TODO create db call to remove task from task table by ID
    public boolean removeTask(int taskId) {
        task = new Task();
        task.setTaskId(taskId);

        if (taskId <= 0) {
            return false;
        } else {
            try {
                resultSetDTO.resultSetForSingleTask(StatementType.DELETE.createStatementWithCondition(task, "task_id"));
                System.out.println("Task has been deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    // TODO create db call that gets all tasks by username
    public LinkedList<HashMap> getAllTasksByUsername(String username) throws ImproperConfigurationException, SQLException, ResourceNotFoundException {
        appUserService = AppState.getInstance().getAppUserService();
        AppUser user = appUserService.findUserByUsername(username);
        int userId = user.getUserID();
        Task task = new Task();
        task.setUserId(userId);

        LinkedList<HashMap> tasks = resultSetDTO.resultSetToLinkedListTask(StatementType.SELECT.createStatementWithCondition(task, "user_id"));
        if (tasks == null) {
            throw new ResourceNotFoundException();
        }

            //used to organize the tasks by task_state, such that false or incomplete tasks, shown first.
            task.setTaskTitle(tasks.get(0).get("task_state").toString());
            List<HashMap> tempList = tasks.stream().sorted((Comparator.comparing(o -> o.get("task_state").toString()))).collect(Collectors.toList());
            LinkedList<HashMap> sortedTasks = new LinkedList<>();
            sortedTasks.addAll(tempList);

            return sortedTasks;

    }

    // TODO create db call that gets all tasks by username
    public LinkedList<HashMap> getAllUncompletedTasks() throws ImproperConfigurationException, SQLException, ResourceNotFoundException {
        Task task1 = new Task();
        task1.setTaskState(false);
        LinkedList<HashMap> tasks = resultSetDTO.resultSetToLinkedListTask(StatementType.SELECT.createStatementWithCondition(task1, "task_state"));

        return tasks;
    }

}

