package ua.training.project.model.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.DBStatement;
import ua.training.project.exception.DBException;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.dao.*;
import ua.training.project.model.entity.ActivityStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle statements to user activity database
 *
 * @author Iryna Radchuk
 * @see ConnectionHandler
 * @see AutoCloseable
 */
public class UserActivityRepository extends ConnectionHandler implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(UserActivityRepository.class);
    private static Connection connection = null;

    private UserActivityRepository() {
    }

    public static UserActivityRepository getInstance() {
        try {
            connection = getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            log.error(throwable.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return new UserActivityRepository();
    }

    /**
     * Get activity id by activity name
     */
    public int getActivityID(String activity) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_FIND)) {
            statement.setString(1, activity);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("activity_id");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return id;
    }

    /**
     * Get user activity status
     */
    public ActivityStatus checkUserActivityStatus(int userId, String activity) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_FIND)) {
            statement.setInt(1, userId);
            statement.setString(2, activity);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return ActivityStatus.ABSENT;
            }
            String status = resultSet.getString("status");
            System.out.println("STATUS" + ActivityStatus.valueOf(status));
            return ActivityStatus.valueOf(status);
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Add new activity with date and time by user
     */
    public void addActivityForUser(int userId, String activity, LocalDate date, int duration, HttpServletRequest request) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(userId, activity) != ActivityStatus.APPROVED) {
            request.setAttribute("error", ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
            throw new TimeTrackerException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        if (timeValidation(date) + duration > 8) {
            request.setAttribute("error", ExceptionMessage.OVERTIME);
            throw new TimeTrackerException(ExceptionMessage.OVERTIME);
        }
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.setDate(3, Date.valueOf(date));
            statement.setInt(4, duration);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            log.error(e.getMessage());
            connection.rollback();
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Get all activities by user id
     */
    public List<UserActivityDao> getAllUserActivities(Integer id) {
        List<UserActivityDao> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.SHOW_All_USER_ACTIVITIES)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserActivityDao userActivity = new UserActivityDao();
                userActivity.setActivityName(resultSet.getString("activity.activity_name"));
                userActivity.setActivityStatus(
                        ActivityStatus.valueOf(resultSet.getString("user_allowed_activity.status")));
                activities.add(userActivity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Get all activities by date for a user
     */
    public List<DateActivityDao> getAllUserActivitiesByDate(int id, LocalDate date) {
        List<DateActivityDao> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.SHOW_USER_ACTIVITIES_BY_DATE)) {
            statement.setInt(1, id);
            statement.setString(2, String.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DateActivityDao userActivity = new DateActivityDao();
                userActivity.setId(id);
                userActivity.setDate(date);
                userActivity.setActivity(resultSet.getString("activity.activity_name"));
                userActivity.setDuration(resultSet.getInt("user_activity.activity_duration"));
                activities.add(userActivity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Get a list of pending activities for admin to approve
     */
    public List<PendingActivity> getAllPendingActivities() {
        List<PendingActivity> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.GET_ALL_PENDING_ACTIVITIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PendingActivity activity = new PendingActivity();
                activity.setUserId(resultSet.getInt("user_id"));
                activity.setEmail(resultSet.getString("user_email"));
                activity.setFirstName(resultSet.getString("user_first_name"));
                activity.setLastName(resultSet.getString("user_last_name"));
                activity.setActivityName(resultSet.getString("activity_name"));
                activities.add(activity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Request activity by user
     */
    public void requestActivity(int userId, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(userId, activity) != ActivityStatus.ABSENT) {
            connection.setAutoCommit(true);
            throw new TimeTrackerException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Approve user activity by admin
     */
    public void approveActivity(int id, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(id, activity) == ActivityStatus.APPROVED) {
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_APPROVE)) {
            statement.setInt(1, id);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Deny user activity by admin
     */
    public void denyActivity(int id, String activity) throws SQLException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(id, activity) == ActivityStatus.ABSENT) {
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        int activityId = getActivityID(activity);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DENY_ACTIVITY)) {
            statement.setInt(1, id);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Check total activities time for certain date
     */
    public int timeValidation(LocalDate date) {
        int time = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_BY_DATE_DURATION)) {
            statement.setString(1, String.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                time = resultSet.getInt("sum(activity_duration)");
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return time;
    }

    /**
     * Get full activity statistics
     */
    public List<ActivityStatisticsDao> getActivityStatistics() {
        List<ActivityStatisticsDao> activityStatistics = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_STATISTICS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ActivityStatisticsDao activity = new ActivityStatisticsDao();
                activity.setActivity(resultSet.getString("activity_name"));
                activity.setCategory(resultSet.getString("category_name"));
                activity.setUsers(resultSet.getInt("user_quantity"));
                activityStatistics.add(activity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return activityStatistics;
    }

    /**
     * Get full user statistics
     */
    public List<UserStatisticsDao> getUserStatistics() {
        List<UserStatisticsDao> userStatistics = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_STATISTICS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserStatisticsDao user = new UserStatisticsDao();
                user.setDate(LocalDate.parse(resultSet.getString("activity_date")));
                user.setEmail(resultSet.getString("user_email"));
                user.setFirstName(resultSet.getString("user_first_name"));
                user.setLastName(resultSet.getString("user_last_name"));
                user.setActivity(resultSet.getString("activity_name"));
                user.setDuration(resultSet.getInt("activity_duration"));
                userStatistics.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
        return userStatistics;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
