package com.revature.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

public class ResultSetService {

    private ResultSet rs;

    public LinkedList<HashMap> resultSetToLinkedList(ResultSet rs) throws SQLException {
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
}
