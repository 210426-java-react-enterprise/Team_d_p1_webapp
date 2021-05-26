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


        while((rs!=null) && rs.next()) {
            resultMap = new HashMap<>();
            resultMap.put("task_id", rs.getInt("task_id"));
            resultMap.put("title", rs.getString("title"));
            resultMap.put("message", rs.getString("message"));
            resultMap.put("date_due", rs.getString("date_due"));
            resultMap.put("task_state", rs.getBoolean("task_state"));
            resultMap.put("user_id", rs.getInt("user_id"));
            taskList.add(resultMap);
        }
        return taskList;
    }

    public AppUser resultSetToUser(ResultSet rs) throws SQLException {
        AppUser user = new AppUser();
        if(rs != null) {
            while(rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setUserID(rs.getInt("user_id"));
            }
        }
        return user;
    }

    public Task resultSetForSingleTask(ResultSet rs) throws SQLException {
        task = new Task();
        while((rs!=null) && rs.next()) {
            task.setTaskId(rs.getInt("taskId"));
            task.setTaskTitle(rs.getString("title"));
            task.setTaskMessage(rs.getString("message"));
            task.setDateDue(rs.getString("dueDate"));

        }
        return task;
    }
}
