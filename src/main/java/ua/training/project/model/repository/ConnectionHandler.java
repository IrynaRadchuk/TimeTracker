package ua.training.project.model.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Class to connect to database
 *
 * @author Iryna Radchuk
 */
public class ConnectionHandler {

    /**
     * Get connection to database
     *
     * @param connectionUrl URL to get connection
     */
    public static Connection getConnection(String connectionUrl) throws SQLException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
        String property = resourceBundle.getString("db.url");
        return DriverManager.getConnection(property);
    }
}
