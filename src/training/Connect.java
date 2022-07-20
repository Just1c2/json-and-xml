package training;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static java.sql.Connection getConn() throws SQLException {
        String dbURL = "jdbc:mysql://localhost:3306/productmanagement";
        String username = "root";
        String password = "";
        java.sql.Connection connection = DriverManager.getConnection(dbURL, username, password);


        return connection;
    }
}
