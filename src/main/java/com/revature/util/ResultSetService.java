package com.revature.util;

import com.revature.entities.AppUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class ResultSetService {

    private ResultSet rs;

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
}
