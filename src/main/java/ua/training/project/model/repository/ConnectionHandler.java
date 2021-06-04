package ua.training.project.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class to connect to database
 *
 * @author Iryna Radchuk
 */
public class ConnectionHandler {

    /**
     * Get connection to database
     */
    public static Connection getConnection() throws SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        try {
            String driver = resourceBundle.getString("db.driver");
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String property = resourceBundle.getString("db.url");
        return DriverManager.getConnection(property);
    }
}
