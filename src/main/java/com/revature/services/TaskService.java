package com.revature.services;

import com.revature.entities.Task;

public class TaskService {

    public Task updateTaskContent(Task task, String newContent){
        task.setTaskMessage(newContent);
        return task;
    }

    public Task updateTaskTitle(Task task, String newTitle){
        task.setTaskTitle(newTitle);
        return task;
    }

    public Task updateTaskDueDate(Task task, String newDueDate){
        task.setDateDue(newDueDate);
        return task;
    }

}
