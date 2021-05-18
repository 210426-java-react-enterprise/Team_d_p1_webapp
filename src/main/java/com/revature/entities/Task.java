package com.revature.entities;

import java.time.LocalDateTime;
//    TODO @Entity and @Table tags here
public class Task {
//    TODO @Column tags here
    private LocalDateTime dateCreated;
    private LocalDateTime dateDue;
    private String taskTitle;
    private String taskMessage;
    private String taskState;


    public Task(LocalDateTime dateCreated, LocalDateTime dateDue, String taskTitle, String taskMessage) {
        this.dateCreated = dateCreated;
        this.dateDue = dateDue;
        this.taskTitle = taskTitle;
        this.taskMessage = taskMessage;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDateTime dateDue) {
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
