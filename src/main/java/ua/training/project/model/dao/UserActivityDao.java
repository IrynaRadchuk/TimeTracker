package ua.training.project.model.dao;

import ua.training.project.controller.command.Command;
import ua.training.project.model.entity.ActivityStatus;

/**
 * DAO class to manage users in database
 *
 * @author Iryna Radchuk
 */
public class UserActivityDao {
    private String activityName;
    private ActivityStatus activityStatus;

    public UserActivityDao() {
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
}
