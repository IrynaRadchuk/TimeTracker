package ua.training.project.model.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        InputStream file = new FileInputStream("C:\\Users\\ira\\IdeaProjects\\TimeTracker\\src\\main\\resources\\db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property = prop.getProperty(connectionUrl);
        return DriverManager.getConnection(property);
    }
}
