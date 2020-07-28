package by.galdovich.task_6.creator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "1234";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/library";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD);
        return connection;
    }
}
