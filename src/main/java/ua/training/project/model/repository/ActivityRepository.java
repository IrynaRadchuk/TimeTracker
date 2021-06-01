package ua.training.project.model.repository;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.project.constant.DBStatement;
import ua.training.project.exception.DBException;
import ua.training.project.exception.ExceptionMessage;
import ua.training.project.exception.TimeTrackerException;
import ua.training.project.model.dao.ActivityDao;
import ua.training.project.model.entity.Activity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle statements to activity database
 *
 * @author Iryna Radchuk
 * @see ConnectionHandler
 * @see AutoCloseable
 */
public class ActivityRepository extends ConnectionHandler implements AutoCloseable {
    private static final Logger log = LogManager.getLogger(ActivityRepository.class);
    private static Connection connection = null;

    private ActivityRepository() {
    }

    public static ActivityRepository getInstance() {
        try {
            connection = getConnection("db.url");
        } catch (SQLException | IOException throwable) {
            log.error(throwable.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return new ActivityRepository();
    }

    /**
     * Get list of activities with categories
     */
    public List<ActivityDao> getActivitiesWithCategories() {
        List<ActivityDao> activities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.GET_ALL_ACTIVITIES_WITH_CATEGORIES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ActivityDao activity = new ActivityDao();
                activity.setId(resultSet.getInt("activity.activity_id"));
                activity.setName(resultSet.getString("activity.activity_name"));
                activity.setCategoryId(resultSet.getInt("activity.category_id"));
                activity.setCategory(resultSet.getString("activity_category.category_name"));
                activities.add(activity);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Get list of activities
     */
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
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return activities;
    }

    /**
     * Update activity by admin
     */
    public void updateActivity(int activityId, String activity, String category) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_CHANGE)) {
            statement.setString(1, activity);
            statement.setString(2, category);
            statement.setInt(3, activityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Delete activity by admin
     */
    public void deleteActivity(int activityId) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_DELETE)) {
            statement.setInt(1, activityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Add activity by admin
     */
    public void createActivity(String activity, String category) {
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.ACTIVITY_CREATE)) {
            statement.setString(1, activity);
            statement.setString(2, category);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBException(ExceptionMessage.DB_CONNECTION);
        }
    }

    /**
     * Get activity category list
     */
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(DBStatement.CATEGORY_GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(resultSet.getString("category_name"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new TimeTrackerException(ExceptionMessage.DB_CONNECTION);
        }
        return categories;
    }

    @Override
    public void close() throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
