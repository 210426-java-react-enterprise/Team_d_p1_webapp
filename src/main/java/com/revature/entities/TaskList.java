package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Table;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

import java.util.List;
import java.util.Vector;
@Table(name="taskList")
@Entity
public class TaskList {

    @PrimaryKey
    private int taskListID;
    @Column(columnName = "userCreated")
    private String userCreated;
    @Column(columnName = "tasks")
    private List<Task> tasks;

    public TaskList(String userCreated) {
        this();
        this.userCreated = userCreated;

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
