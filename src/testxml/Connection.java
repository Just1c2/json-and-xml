package testxml;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    public static java.sql.Connection getCon() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/studentmanagement";
        String username = "root";
        String password = "";
        java.sql.Connection connection = DriverManager.getConnection(dbURL, username, password);

        return connection;
    }
}
