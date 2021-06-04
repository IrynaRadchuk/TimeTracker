package ua.training.project.model.dao;

import ua.training.project.model.entity.ActivityStatus;

import java.util.Objects;

/**
 * DAO class to manage user activities in database
 *
 * @author Iryna Radchuk
 */
public class UserActivityDao {
    private String activityName;
    private ActivityStatus activityStatus;
    private String activityUa;

    public String getActivityUa() {
        return activityUa;
    }

    public void setActivityUa(String activityUa) {
        this.activityUa = activityUa;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserActivityDao)) return false;
        UserActivityDao that = (UserActivityDao) o;
        return Objects.equals(activityName, that.activityName) &&
                activityStatus == that.activityStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityName, activityStatus);
    }

    @Override
    public String toString() {
        return "UserActivityDao{" +
                "activityName='" + activityName + '\'' +
                ", activityStatus=" + activityStatus +
                '}';
    }
}
