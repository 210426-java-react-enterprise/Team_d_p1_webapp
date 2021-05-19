package com.revature.entities;

import com.revature.annotations.Column;
import com.revature.annotations.Entity;
import com.revature.annotations.PrimaryKey;
import com.revature.annotations.Table;

import java.util.List;
import java.util.Vector;
// TODO @Entity and @Table tags here
@Entity
@Table(name= "taskList")
public class TaskList {
// TODO @Column Tags here
    
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


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskList{");
        sb.append("taskListID=").append(taskListID);
        sb.append(", userCreated='").append(userCreated).append('\'');
        sb.append(", tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }
}
