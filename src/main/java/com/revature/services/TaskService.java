package com.revature.services;

import com.revature.entities.Task;
import com.revature.exception.ImproperConfigurationException;
import com.revature.exceptions.DateFormatException;
import com.revature.exceptions.MessageLengthOutOfBoundsException;
import com.revature.exceptions.TitleLengthOutOFBoundsException;
import com.revature.statements.StatementType;
import com.revature.util.ResultSetDTO;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class TaskService {

    private Task task;
    private final Pattern datePattern = Pattern.compile("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
    private final ResultSetDTO resultSetDTO;

    public TaskService(ResultSetDTO resultSetDTO) {
        this.resultSetDTO = resultSetDTO;
    }

    public boolean updateTaskContent(int taskId, String newContent) throws ImproperConfigurationException, SQLException {

        if(!contentValidation(newContent)) {
            throw new MessageLengthOutOfBoundsException("Message too long - limit 250 characters");
        } else {

            task = new Task();
            task.setTaskMessage(newContent);
            task.setTaskId(taskId);

            try {
                Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
                actualTask.setTaskMessage(newContent);
                actualTask.setTaskId(taskId);
                StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;

    }

    public boolean updateTaskTitle(int taskId, String newTitle) throws ImproperConfigurationException, SQLException {
        if(!titleValidation(newTitle)) {

            throw new TitleLengthOutOFBoundsException("Title too long - limit 50 characters");

        } else {

            task = new Task();
            task.setTaskTitle(newTitle);
            task.setTaskId(taskId);

            try {
                Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
                actualTask.setTaskTitle(newTitle);
                actualTask.setTaskId(taskId);
                StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;
    }

    public boolean updateTaskDueDate(int taskId, String newDueDate) throws ImproperConfigurationException, SQLException {
        if(!dateValidation(newDueDate)) {

            throw new DateFormatException("Please format dates mm/dd/yyyy");

        } else {

            task = new Task();

            task.setDateDue(newDueDate);
            task.setTaskId(taskId);

            try {
                Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
                actualTask.setDateDue(newDueDate);
                actualTask.setTaskId(taskId);
                StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;
    }

    public boolean updateTaskState(int taskId) throws ImproperConfigurationException {
       task = new Task();
       task.setTaskId(taskId);

        try {
            Task actualTask = resultSetDTO.resultSetForSingleTask(StatementType.SELECT.createStatementWithCondition(task, "task_id"));
            actualTask.setTaskState(!actualTask.getTaskState());
            StatementType.UPDATE.createStatementWithCondition(actualTask, "task_id");
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

        return true;
    }

    private boolean contentValidation(String content) {
        if(content.length() > 250) {
            return false;
        }
        return true;
    }

    private boolean titleValidation(String title) {
        if(title.length() > 50) {

            return false;
        }
        return true;
    }

    private boolean dateValidation(String date) {
        if(!datePattern.matcher(date).matches()) {

            return false;
        }
        return true;
    }

}
