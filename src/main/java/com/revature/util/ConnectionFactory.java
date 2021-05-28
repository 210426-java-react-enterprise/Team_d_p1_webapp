package com.revature.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;
    private static Properties props = new Properties();

    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public static Connection setConnection(String hostURL, String username, String password, String schemaName) {

        String processedHostURL = "jdbc:mysql://" + hostURL + "/postgres?schemaname:" + schemaName;
        props.put("host-url", processedHostURL);
        props.put("username", username);
        props.put("password", password);

        return null;
    }
    }

