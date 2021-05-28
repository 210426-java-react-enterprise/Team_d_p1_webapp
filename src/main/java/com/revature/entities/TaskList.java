package com.revature.entities;

import java.util.List;
import java.util.Vector;

public class TaskList {

    private int taskListID;

    private String userCreated;

    private List<Task> tasks;

    public TaskList(String userCreated) {
        this();
        this.userCreated = userCreated;

    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(){
        tasks = new Vector<>();
    }

    public int getTaskListID() {
        return taskListID;
    }

    public void setTaskListID(int taskListID) {
        this.taskListID = taskListID;
    }

    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
