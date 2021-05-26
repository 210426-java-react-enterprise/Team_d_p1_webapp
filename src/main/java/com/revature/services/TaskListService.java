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
    private AppUser user;
    private final ResultSetService resultSetService;
    private final AppUserService appUserService = AppState.getInstance().getAppUserService();


    public TaskListService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }


    //    TODO create database call to ORM to persist task
    public void addTask(Task newTask) {
        try {
            resultSetService.resultSetToLinkedListTask(StatementType.INSERT.createStatementWithCondition(newTask, "message"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Hypothetical task has been added: " + newTask.toString());
    }

    //    TODO create db call to remove task from task table by ID
    public void removeTask(int taskId) {
        try {
            resultSetService.resultSetToLinkedListTask(StatementType.DELETE.createStatementWithCondition(taskId, "task_Id"));
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


    //    TODO
    public void createTaskList() {
    }


    //    TODO
    public void setTaskListComplete() {

        try {
            resultSetService.resultSetToLinkedListTask(StatementType.UPDATE.createStatementWithCondition(newTask, "taskState"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ImproperConfigurationException e) {
            e.printStackTrace();
        }

    }


    //    TODO
    public boolean updateTaskListToDB() {
        boolean isTaskUpdated = false;

        try {
            LinkedList<HashMap> resultTaskList = resultSetService.resultSetToLinkedListTask(StatementType.UPDATE.createStatementWithCondition(newTask, "task_List"));


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ImproperConfigurationException e) {
            e.printStackTrace();
        }

        return false;
    }

    //    TODO
    public void getTaskListFromDB() {

        try {

            LinkedList<HashMap> allList = resultSetService.resultSetToLinkedListTask(StatementType.SELECT.createStatementWithCondition(taskList, "task_List"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ImproperConfigurationException e) {
            e.printStackTrace();
        }


    }


    //Is this needed?
    public void getTaskListsByUser() {


    }

    //    TODO Generates a report of all tasks in a task list.
    public void generateTaskReport() {

    }

    //    TODO Generates a report of all tasks in a task list that are due by a specific date and time
    public void generateTaskReportByDate(LocalDateTime byDateTime) {

    }

    //    TODO Reverse the set isPublic flag for a task
    public void updatePublicView() {

    }




    //    TODO
    public void getTasksCompletedOnTime() {

    }

    //    TODO
    public void getTasksNotCompletedInTime() {
        Task task;
//            Stream<Task> nonCompletedTasks = taskList.getTasks().stream();
//                        .filter(task -> !task.getTaskState())
//                        .count();


    }

    //    TODO
    public void getUncompletedTasks() {

    }

}
