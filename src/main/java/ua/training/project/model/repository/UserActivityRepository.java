package ua.training.project.model.repository;

import ua.training.project.constant.DBStatement;
import ua.training.project.exception.DBException;
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
    public int getUserID(String email){
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_FIND)){
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("user_id");
            }
        } catch (SQLException e) {
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return id;
    }
    public int getActivityID(String activity){
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_FIND)){
            statement.setString(1,activity);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("activity_id");
            }
        } catch (SQLException e) {
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return id;
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
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }

    }

    public void addActivityForUser(String email, String activity, LocalDate date, int duration) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(email, activity) != ActivityStatus.APPROVED) {
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        if (timeValidation(date)+duration>8){
            throw new TimeTrackerException(ExceptionMessage.OVERTIME);
        }
        int userId = getUserID(email);
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.setDate(3, Date.valueOf(date));
            statement.setInt(4, duration);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DBException(ExceptionMessage.DB_CONNECTION);
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
        } catch (SQLException e) {
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    public void requestActivity(String email, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(email, activity) != ActivityStatus.ABSENT) {
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int userId = getUserID(email);
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void approveActivity(String email, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(email, activity) == ActivityStatus.APPROVED) {
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int userId = getUserID(email);
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_APPROVE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.executeQuery();
            connection.commit();
        } catch (SQLException throwable) {
            connection.rollback();
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public void denyActivity(String email, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(email, activity) == ActivityStatus.ABSENT) {
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        int userId = getUserID(email);
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DENY_ACTIVITY)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.executeQuery();
            connection.commit();
        } catch (SQLException throwable) {
            connection.rollback();
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public int timeValidation(LocalDate date) {
        int time = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_BY_DATE_DURATION)) {
            statement.setString(1, String.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
               time = resultSet.getInt("sum(activity_duration)");
            }
        } catch (SQLException e) {
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return time;
    }
        @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
