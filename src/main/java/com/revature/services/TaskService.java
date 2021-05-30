package com.revature.services;

import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetDTO;

import java.sql.SQLException;

public class TaskService {

    private Task task;
    private Task resultTask;
    private final ResultSetDTO resultSetDTO;

    public TaskService(ResultSetDTO resultSetDTO) {
        this.resultSetDTO = resultSetDTO;
    }

    public boolean updateTaskContent(int taskId, String newContent) throws ImproperConfigurationException, SQLException {
        task = new Task();
        resultTask = new Task();
        task.setTaskMessage(newContent);
        task.setTaskId(taskId);

        Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));

        actualTask.setTaskMessage(newContent);
        actualTask.setTaskId(taskId);

        try {
            StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
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

        Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));

        actualTask.setTaskTitle(newTitle);
        actualTask.setTaskId(taskId);
        System.out.println("actual task" + actualTask);

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

    public boolean updateTaskDueDate(int taskId, String newDueDate) throws ImproperConfigurationException, SQLException {
        task = new Task();
        task.setDateDue(newDueDate);
        task.setTaskId(taskId);

        Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));

        actualTask.setDateDue(newDueDate);
        actualTask.setTaskId(taskId);

        try {
            StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean updateTaskState(int taskId) throws ImproperConfigurationException {
       task = new Task();
       task.setTaskId(taskId);

        try {
            Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
            System.out.println("before: " + actualTask);
            actualTask.setTaskState(!actualTask.getTaskState());
            System.out.println("\n\n\nafter: "+actualTask);
            StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
