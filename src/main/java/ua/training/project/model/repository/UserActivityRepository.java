package ua.training.project.model.repository;

import ua.training.project.constant.DBStatement;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.entity.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserActivityRepository implements AutoCloseable {
    private static Connection connection = null;

    private UserActivityRepository() {
    }

    public static Connection getConnection(String connectionUrl) throws SQLException, IOException {
        InputStream file = new FileInputStream("C:\\Users\\ira\\IdeaProjects\\TimeTracker\\src\\main\\resources\\db.properties");
        Properties prop = new Properties();
        prop.load(file);
        String property = prop.getProperty(connectionUrl);
        System.out.println(property);
        return DriverManager.getConnection(property);
    }

    public static UserActivityRepository getInstance() {
        try {
            connection = UserActivityRepository.getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return new UserActivityRepository();
    }

    public ActivityStatus checkUserActivityStatus(String email, String activity) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_FIND)) {
            statement.setString(1, email);
            statement.setString(2, activity);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return ActivityStatus.ABSENT;
            }
            String status = resultSet.getString("status");
            return ActivityStatus.valueOf(status);
        } catch (SQLException e) {
            System.out.println("EXCEPT");
            throw new RuntimeException(e);
        }

    }

    public void addActivityForUser(String email, String activity, LocalDate date, int duration) throws SQLException {
        if (checkUserActivityStatus(email, activity) != ActivityStatus.APPROVED) {
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        connection.setAutoCommit(false);
        int userId = 0;
        int activityId = 0;
        try (PreparedStatement userStatement = connection.prepareStatement(DBStatement.USER_FIND);
             PreparedStatement activityStatement = connection.prepareStatement(DBStatement.ACTIVITY_FIND);
             PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ACTIVITY_CREATE)) {
            userStatement.setString(1, email);
            activityStatement.setString(1, activity);
            ResultSet userResult = userStatement.executeQuery();
            if (userResult.next()) {
                userId = userResult.getInt("user_id");
            }
            ResultSet activityResult = activityStatement.executeQuery();
            if (activityResult.next()) {
                activityId = activityResult.getInt("activity_id");
            }
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.setDate(3, Date.valueOf(date));
            statement.setInt(4, duration);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<UserActivity> getAllUserActivities(String email) {
        List<UserActivity> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.SHOW_USER_ACTIVITIES)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserActivity userActivity = new UserActivity();
                userActivity.setUser(new User(resultSet.getInt("user.user_id"),
                        resultSet.getString("user.user_email"),
                        resultSet.getString("user.user_password"),
                        resultSet.getString("user.user_first_name"),
                        resultSet.getString("user.user_last_name"),
                        Role.getRoleById(resultSet.getInt("user.role_id"))));
                userActivity.setActivity(new Activity(resultSet.getInt("activity.activity_id"),
                        resultSet.getString("activity.activity_name"),
                        resultSet.getInt("activity.category_id")));
                userActivity.setDate(LocalDate.parse(resultSet.getString("user_activity.activity_date")));
                userActivity.setDuration(resultSet.getInt("user_activity.activity_duration"));
                activities.add(userActivity);
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
