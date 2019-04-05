package com.bespalov.shop.database;

import com.bespalov.shop.config.Properties;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    private java.sql.Connection connection;
    private String login = Properties.getResourceBundle().getString("login");
    private String password = Properties.getResourceBundle().getString("password");
    private String url = "jdbc:mysql://localhost:8080/server?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Statement statement;
    private PreparedStatement preparedStatement;

    public Connection() throws ClassNotFoundException {
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement initConnection() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public PreparedStatement getPreparedStatement(String msg) {
        try {
            preparedStatement = connection.prepareStatement(msg);
            preparedStatement.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
}
