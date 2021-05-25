package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.PrimaryKey;
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
    private boolean taskState;
    @PrimaryKey
    private int taskId;

    public Task(String dateDue, String taskTitle, String taskMessage) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.taskState = false;
    }

    // Constructor for getting task from database or from front end to be updated in db
    public Task(String dateDue, String taskTitle, String taskMessage, boolean taskState, int taskId) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.taskId = taskId;
        this.taskState = taskState;
    }

    // Constructor for put to update task
    public Task(String dateDue, String taskTitle, String taskMessage, int taskId) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.taskId = taskId;
        this.taskState = false;
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

    public boolean getTaskState() {
        return this.taskState;
    }
    public void setTaskState() {
        this.taskState = !taskState;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Task{");
        sb.append("dateDue='").append(dateDue).append('\'');
        sb.append(", taskTitle='").append(taskTitle).append('\'');
        sb.append(", taskMessage='").append(taskMessage).append('\'');
        sb.append(", taskState='").append(taskState).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
