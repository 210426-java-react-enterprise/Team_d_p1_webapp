package com.revature.services;

import com.revature.entities.Task;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetService;

public class TaskService {

    private Task task;
    private Task resultTask;
    private final ResultSetService resultSetService;

    public TaskService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }

    public boolean updateTaskContent(String title, String newContent){
        task = new Task();
        resultTask = new Task();
        task.setTaskMessage(newContent);
        task.setTaskTitle(title);

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

    public boolean updateTaskTitle(String newTitle){
        task = new Task();
        resultTask = new Task();
        task.setTaskTitle(newTitle);

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

    public boolean updateTaskDueDate(String title, String newDueDate){
        task = new Task();
        resultTask = new Task();
        task.setTaskTitle(title);
        task.setDateDue(newDueDate);

        try {
            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "date_due"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(resultTask != null) {
            return true;
        }
        return false;
    }

//    public boolean updateTaskState(int taskId) {
//        task.getTaskState();
//
//        try {
//            resultTask = resultSetService.resultSetForSingleTask(StatementType.UPDATE.createStatementWithCondition(task, "taskState"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        if(resultTask != null) {
//            return true;
//        }
//        return false;
//    }

}
