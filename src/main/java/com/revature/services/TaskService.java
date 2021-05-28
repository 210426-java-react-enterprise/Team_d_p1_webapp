package com.revature.services;

import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetService;

import java.sql.SQLException;

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

    public boolean updateTaskTitle(int taskId, String newTitle) throws ImproperConfigurationException, SQLException {
        task = new Task();
        resultTask = new Task();
        task.setTaskTitle(newTitle);
        task.setTaskId(taskId);

        Task actualTask = resultSetService.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
        System.out.println("actual task" + actualTask);
        actualTask.setTaskTitle(newTitle);

        try {
//            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "task_id"));
            StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
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
        task.getTaskState();

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
