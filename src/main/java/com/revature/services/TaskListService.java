package com.revature.services;

import com.revature.entities.Task;
import com.revature.util.ResultSetService;

import java.time.LocalDateTime;

public class TaskListService {

    private Task newTask;
    private ResultSetService resultSetService;

    public TaskListService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }

    //    TODO create database call to ORM to persist task
    public void addTask(Task newTask) {
        System.out.println("Hypothetical task has been added: " + newTask.toString());
    }

    //    TODO create db call to remove task from task table by ID
    public void removeTask(int taskId) {
        System.out.println("Task has been deleted");

    }

    // TODO create db call that gets all tasks by username
    public void getAllTasksByUsername(String username) {

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

    }

    //    TODO
    public void getUncompletedTasks() {

    }

    //    TODO
    public void createTaskList() {

    }

    //    TODO
    public void setTaskListComplete() {

    }

    //    TODO
    public void updateTaskListToDB() {

    }

    //    TODO
    public void getTaskListFromDB() {

    }

    //    TODO
    public void getTaskListsByUser() {

    }
}
