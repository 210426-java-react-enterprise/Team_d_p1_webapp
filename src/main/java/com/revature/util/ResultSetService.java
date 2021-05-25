package com.revature.util;

import com.revature.entities.AppUser;
import com.revature.entities.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class ResultSetService {

    private ResultSet rs;
    private Task task;

    public LinkedList<HashMap> resultSetToLinkedListTask(ResultSet rs) throws SQLException {
        HashMap<String, Object> resultMap = new HashMap<>();
        LinkedList<HashMap> taskList = new LinkedList<>();
        while(rs.next()) {
            resultMap.put("taskId", rs.getInt("taskId"));
            resultMap.put("title", rs.getString("title"));
            resultMap.put("message", rs.getString("message"));
            resultMap.put("dueDate", rs.getString("dueDate"));
            resultMap.put("taskState", rs.getBoolean("taskState"));
            taskList.add(resultMap);
        }
        return taskList;
    }

    public AppUser resultSetToUser(ResultSet rs) throws SQLException {
        AppUser user = new AppUser();
        while(rs.next()) {
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setAge(rs.getInt("age"));
        }
        return user;
    }

    public Task resultSetForSingleTask(ResultSet rs) throws SQLException {
        task = new Task();
        while(rs.next()) {
            task.setTaskId(rs.getInt("taskId"));
            task.setTaskTitle(rs.getString("title"));
            task.setTaskMessage(rs.getString("message"));
            task.setDateDue(rs.getString("dueDate"));
        }
        return task;
    }
}
