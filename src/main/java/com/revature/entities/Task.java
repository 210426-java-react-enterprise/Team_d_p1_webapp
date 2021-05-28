package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

@Table(name="tasks")
@Entity
public class Task {
    @Column(columnName = "date_due")
    private String dateDue;
    @Column(columnName = "title")
    private String taskTitle;
    @Column(columnName = "message")
    private String taskMessage;
    @Column(columnName = "task_state")
    private boolean taskState;
    @PrimaryKey
    private int taskId;
    @Column(columnName = "user_id")
    private int userId;



    // Constructor for getting task from database or from front end to be updated in db
    public Task(String dateDue, String taskTitle, String taskMessage, boolean taskState, int taskId) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.taskId = taskId;
        this.taskState = taskState;
    }

    // Constructor for put to update task
    public Task(String dateDue, String taskTitle, String taskMessage, int userId) {
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.userId = userId;
        this.taskState = false;
    }


    public Task(String taskTitle, String taskMessage, String dateDue){
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
        this.dateDue = dateDue;

    }

    public Task() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
