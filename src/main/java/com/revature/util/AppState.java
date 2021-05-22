package com.revature.util;

import com.revature.services.AppUserService;
import com.revature.services.TaskListService;

public class AppState {

    private static AppState appState;
    private static ResultSetService resultSetService = new ResultSetService();

    private AppState() {

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
}
