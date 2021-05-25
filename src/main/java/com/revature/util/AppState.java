package com.revature.util;

import com.revature.services.AppUserService;
import com.revature.services.TaskListService;
import com.revature.services.TaskService;
import com.revature.util.datasource.ConnectionFactory;


public class AppState {

    private static AppState appState;
    private static ResultSetService resultSetService = new ResultSetService();

    private AppState() {

    }

    static {
        ConnectionFactory.setConnection(
                System.getenv("host_url"),
                System.getenv("db_username"),
                System.getenv("db_password"),
                "schema_name"
        );
    }

    public static AppState getInstance() {
        if (appState == null) {
            appState = new AppState();
        }
        return appState;
    }
    public static ResultSetService getResultService(){
        return resultSetService;
    }

    public static AppUserService getAppUserService(){
        return new AppUserService(resultSetService);
    }

    public static TaskListService getTaskListService(){
        return new TaskListService(resultSetService);
    }

    public static TaskService getTaskService() { return new TaskService(resultSetService); };

}
