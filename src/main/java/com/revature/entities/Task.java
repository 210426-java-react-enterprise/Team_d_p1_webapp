package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.Table;

@Table(name="tasks")
@Entity
public class Task {
    @Column(columnName = "dateDue")
    private String dateDue;
    @Column(columnName = "title")
    private String taskTitle;
    @Column(columnName = "message")
    private String taskMessage;
    @Column(columnName = "taskState")
    private String taskState;


    public Task(String dateDue, String taskTitle, String taskMessage, String taskState) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskMessage() {
        return taskMessage;
    }

    public void setTaskMessage(String taskMessage) {
        this.taskMessage = taskMessage;
    }

    public String getTaskState() {
        return this.taskState;
    }
    public void setTaskState(String taskState) {
        this.taskState = taskState;
    }
}
