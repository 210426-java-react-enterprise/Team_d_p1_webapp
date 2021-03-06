package com.revature.util;

import com.revature.services.AppUserService;
import com.revature.services.TaskListService;
import com.revature.services.TaskService;
import com.revature.util.datasource.ConnectionFactory;


public class AppState {

    private static AppState appState;
    private static ResultSetDTO resultSetDTO = new ResultSetDTO();
    private static AppUserService appUserService;

    private AppState() {

    }

    static {
        ConnectionFactory.setConnection(
                System.getenv("host_url"),
                System.getenv("db_username"),
                System.getenv("db_password"),
                "test"
        );
        @Deprecated
        ORMState ormState = ORMState.getInstance();
    }

//    public static AppState getInstance() {
//        if (appState == null) {
//            appState = new AppState();
//        }
//        return appState;
//    }
    public static ResultSetDTO getResultService(){
        return resultSetDTO;
    }

    public static AppUserService getAppUserService(){
        return new AppUserService(resultSetDTO);
    }

    public static TaskListService getTaskListService(){
        return new TaskListService(resultSetDTO,new AppUserService(resultSetDTO));
    }

    public static TaskService getTaskService() { return new TaskService(resultSetDTO); };

}
