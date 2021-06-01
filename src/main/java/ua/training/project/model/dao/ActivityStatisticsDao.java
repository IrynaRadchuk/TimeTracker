package ua.training.project.model.dao;

import ua.training.project.controller.command.Command;

/**
 * DAO class to get activity statistics from database
 *
 * @author Iryna Radchuk
 */
public class ActivityStatisticsDao {
    private String activity;
    private String category;
    private int users;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}
