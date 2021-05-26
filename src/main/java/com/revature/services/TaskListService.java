package com.revature.services;

import com.revature.entities.Task;
import com.revature.entities.TaskList;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetService;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TaskListService {
    private TaskList taskList;
    private Task newTask;
    private ResultSetService resultSetService;

    public TaskListService(ResultSetService resultSetService) {
        this.resultSetService = resultSetService;
    }


    //    TODO create database call to ORM to persist task
    public void addTask(Task newTask) {
        try{
            resultSetService.resultSetToLinkedListTask(StatementType.INSERT.createStatement(newTask));

        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Hypothetical task has been added: " + newTask.toString());
    }

    //    TODO create db call to remove task from task table by ID
    public void removeTask(int taskId) {
        try{
            resultSetService.resultSetToLinkedListTask(StatementType.DELETE.createStatementWithCondition(taskId, "task_Id"));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Task has been deleted");
    }

    // TODO create db call that gets all tasks by username
    public void getAllTasksByUsername(String username) {
        try{
            LinkedList<HashMap> list = resultSetService.resultSetToLinkedListTask(StatementType.SELECT.createStatementWithCondition(username,"user_id"));
            taskList = new TaskList();



            LinkedList<Task> temp = new LinkedList<>();
            for (HashMap hm: list) {
                Task tempTask = new Task();
                tempTask.setTaskId(Integer.parseInt(hm.get("task_id").toString()));
                tempTask.setTaskTitle(hm.get("title").toString());
                tempTask.setTaskMessage(hm.get("message").toString());
                tempTask.setDateDue(hm.get("due_date").toString());
                temp.add(tempTask);
            }

            taskList.setTasks(temp);
            taskList.setUserCreated(username);
            // TODO do we need a TaskListID?
            // taskList.setTaskListID();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //    TODO Generates a report of all tasks in a task list.
    public void generateTaskReport() {

    }

    //    TODO Generates a report of all tasks in a task list that are due by a specific date and time
    public void generateTaskReportByDate(LocalDateTime byDateTime) {

    }

    //    TODO Reverse the set isPublic flag for a task
    public void updatePublicView() {

    }

    //    TODO
    public void getTasksCompletedOnTime() {

    }

    //    TODO
    public void getTasksNotCompletedInTime() {

    }

    //    TODO
    public void getUncompletedTasks() {

    }

    //    TODO
    public void createTaskList() {

    }

    //    TODO
    public void setTaskListComplete() {

    }

    //    TODO
    public void updateTaskListToDB() {

    }

    //    TODO
    public void getTaskListFromDB() {

    }

    //    TODO
    public void getTaskListsByUser() {

    }
}
