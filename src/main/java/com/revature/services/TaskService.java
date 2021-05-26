package com.revature.services;

import com.revature.entities.Task;
import com.revature.entities.TaskList;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetService;

public class TaskService {
    private Task task;
    private Task resultTask;
    private final ResultSetService resultSetService;

    public TaskService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }

    public boolean updateTaskContent(int taskId, String newContent){
        task = new Task();
        task.setTaskMessage(newContent);
        task.setTaskId(taskId);

        try {
            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "message"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultTask != null) {
            return true;
        }
        return false;

    }

    public boolean updateTaskTitle(int taskId, String newTitle){
        task.setTaskTitle(newTitle);
        task.setTaskId(taskId);

        try {
            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "title"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultTask != null) {
            return true;
        }
        return false;
    }

    public boolean updateTaskDueDate(int taskId, String newDueDate){
        task.setDateDue(newDueDate);
        task.setTaskId(taskId);
        try {
            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "dateDue"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultTask != null) {
            return true;
        }
        return false;
    }

    public boolean updateTaskState(int taskId) {
        task.setTaskState();

        try {
            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "taskState"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultTask != null) {
            return true;
        }
        return false;
    }

}
