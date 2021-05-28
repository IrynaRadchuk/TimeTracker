package ua.training.project.model.repository;

import ua.training.project.constant.DBStatement;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.entity.Activity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ActivityRepository implements AutoCloseable {
    private static Connection connection = null;

    private ActivityRepository() {
    }

    public static Connection getConnection(String connectionUrl) throws SQLException, IOException {
        InputStream file = new FileInputStream("C:\\Users\\ira\\IdeaProjects\\TimeTracker\\src\\main\\resources\\db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property = prop.getProperty(connectionUrl);
        System.out.println(property);
        return DriverManager.getConnection(property);
    }

    public static ActivityRepository getInstance() {
        try {
            connection = UserActivityRepository.getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return new ActivityRepository();
    }
    public List<Activity> getAllActivities() {
        List<Activity> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.GET_ALL_ACTIVITIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Activity activity = new Activity();
                activity.setId(resultSet.getInt("activity_id"));
                activity.setName(resultSet.getString("activity_name"));
                activity.setCategoryId(resultSet.getInt("category_id"));
                activities.add(activity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return activities;
    }
    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
