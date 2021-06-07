package ua.training.project.model.repository;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.DBStatement;
import ua.training.project.exception.DBException;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.PermissionDeniedException;
import ua.training.project.model.dao.*;
import ua.training.project.model.entity.ActivityStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.training.project.constant.Attributes.*;
import static ua.training.project.constant.Path.ERROR_PAGE;
import static ua.training.project.constant.SessionCall.ERROR;

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
            connection = getConnection();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return new UserActivityRepository();
    }

    /**
     * Get activity id by activity name
     */
    public int getActivityID(String activity, String activityUa) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_FIND)) {
            statement.setString(1, activity);
            statement.setString(2, activityUa);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(ACTIVITY_ID);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return id;
    }

    /**
     * Get user activity status
     */
    public ActivityStatus checkUserActivityStatus(int userId, String activity, String activityUa) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_FIND)) {
            statement.setInt(1, userId);
            statement.setString(2, activity);
            statement.setString(3, activityUa);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return ActivityStatus.ABSENT;
            }
            return ActivityStatus.valueOf(resultSet.getString(STATUS));
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Check activity presence on certain date
     */
    public boolean checkActivityInDB(int userId, int activityId, LocalDate date) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.CHECK_ACTIVITY_PRESENCE)) {
            statement.setInt(1, activityId);
            statement.setString(2, String.valueOf(date));
            statement.setInt(3, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return false;
    }

    /**
     * Add new activity with date and time by user
     */
    public void addActivityForUser(int userId, String activity, String activityUA, LocalDate date, String durationString) throws SQLException, PermissionDeniedException {
        connection.setAutoCommit(false);
        int activityId = getActivityID(activity, activityUA);
        if (StringUtils.isEmpty(durationString)) {
            throw new PermissionDeniedException(ExceptionMessage.WRONG_TIME);
        }
        int duration = Integer.parseInt(durationString);
        if (duration < 1 || duration > 8) {
            throw new PermissionDeniedException(ExceptionMessage.WRONG_TIME);
        }
        if (checkUserActivityStatus(userId, activity, activityUA) != ActivityStatus.APPROVED) {
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        if (timeValidation(userId, date) + duration > 8) {
            throw new PermissionDeniedException(ExceptionMessage.OVERTIME);
        }
        if (checkActivityInDB(userId, activityId, date)) {
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_STORED);
        }
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.setDate(3, Date.valueOf(date));
            statement.setInt(4, duration);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
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
                userActivity.setActivityName(resultSet.getString(ACTIVITY_ACTIVITY_NAME));
                userActivity.setActivityStatus(
                        ActivityStatus.valueOf(resultSet.getString(ACTIVITY_STATUS)));
                userActivity.setActivityUa(resultSet.getString(ACTIVITY_ACTIVITY_UA));
                activities.add(userActivity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR, ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Get all activities by date for a user
     */
    public List<DateActivityDao> getAllUserActivitiesByDate(int id) {
        List<DateActivityDao> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.SHOW_USER_ACTIVITIES_BY_DATE)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DateActivityDao userActivity = new DateActivityDao();
                userActivity.setId(id);
                userActivity.setDate(LocalDate.parse(resultSet.getString(USER_ACTIVITY_ACTIVITY_DATE)));
                userActivity.setActivity(resultSet.getString(ACTIVITY_ACTIVITY_NAME));
                userActivity.setDuration(resultSet.getInt(USER_ACTIVITY_ACTIVITY_DURATION));
                userActivity.setNameUa(resultSet.getString(ACTIVITY_ACTIVITY_UA));
                activities.add(userActivity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Delete activity on certain date by user
     */
    public void deleteActivityTime(int id, LocalDate date) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.DELETE_ACTIVITY_TIME)) {
            statement.setInt(1, id);
            statement.setString(2, String.valueOf(date));
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
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
                activity.setUserId(resultSet.getInt(USERS_ID));
                activity.setEmail(resultSet.getString(USERS_EMAIL));
                activity.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                activity.setLastName(resultSet.getString(USERS_LAST_NAME));
                activity.setActivityName(resultSet.getString(ACTIVITY_NAME));
                activities.add(activity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Request activity by user
     */
    public void requestActivity(int userId, String activity, String activityUA) throws SQLException, PermissionDeniedException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(userId, activity, activityUA) != ActivityStatus.ABSENT) {
            connection.setAutoCommit(true);
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int activityId = getActivityID(activity, activityUA);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_CREATE)) {
            statement.setInt(1, userId);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Approve user activity by admin
     */
    public void approveActivity(int id, String activity) throws SQLException, PermissionDeniedException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(id, activity, EMPTY) == ActivityStatus.APPROVED) {
            connection.setAutoCommit(true);
            throw new PermissionDeniedException(ExceptionMessage.ACTIVITY_ALREADY_APPROVED);
        }
        int activityId = getActivityID(activity, EMPTY);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_ALLOWED_ACTIVITY_APPROVE)) {
            statement.setInt(1, id);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Deny user activity by admin
     */
    public void denyActivity(int id, String activity) throws SQLException, PermissionDeniedException {
        connection.setAutoCommit(false);
        if (checkUserActivityStatus(id, activity, EMPTY) == ActivityStatus.ABSENT) {
            connection.setAutoCommit(true);
            throw new PermissionDeniedException(ExceptionMessage.NOT_AVAILABLE_ACTIVITY);
        }
        int activityId = getActivityID(activity, EMPTY);
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_DENY_ACTIVITY)) {
            statement.setInt(1, id);
            statement.setInt(2, activityId);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        } finally {
            connection.setAutoCommit(true);
        }
    }

    /**
     * Check total activities time for certain date
     */
    public int timeValidation(int id, LocalDate date) {
        int time = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_BY_DATE_DURATION)) {
            statement.setString(1, String.valueOf(date));
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                time = resultSet.getInt(SUM_ACTIVITY_DURATION);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
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
                activity.setActivity(resultSet.getString(ACTIVITY_NAME));
                activity.setCategory(resultSet.getString(CATEGORY_NAME));
                activity.setUsers(resultSet.getInt(USER_QUANTITY));
                activityStatistics.add(activity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return activityStatistics;
    }

    /**
     * Get full user statistics
     */
    public List<UserStatisticsDao> getUserStatistics(int currentPage) {
        List<UserStatisticsDao> userStatistics = new ArrayList<>();
        int recordsPerPage = 10;
        int start = currentPage * recordsPerPage - recordsPerPage;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.USER_STATISTICS_LIMIT)) {
            statement.setInt(1, start);
            statement.setInt(2, recordsPerPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserStatisticsDao user = new UserStatisticsDao();
                user.setDate(LocalDate.parse(resultSet.getString(ACTIVITY_DATE)));
                user.setEmail(resultSet.getString(USERS_EMAIL));
                user.setFirstName(resultSet.getString(USERS_FIRST_NAME));
                user.setLastName(resultSet.getString(USERS_LAST_NAME));
                user.setActivity(resultSet.getString(ACTIVITY_NAME));
                user.setDuration(resultSet.getInt(ACTIVITY_DURATION));
                userStatistics.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return userStatistics;
    }

    /**
     * Get number of rows in database table for pagination
     */
    public Integer getNumberOfRows() {
        Integer numOfRows = 0;
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ROWS_NUMBER)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                numOfRows = resultSet.getInt(COUNT_DATES);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ERROR_PAGE, ExceptionMessage.DB_CONNECTION);
        }
        return numOfRows;
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(connection) && !connection.isClosed()) {
            connection.close();
        }
    }
}
