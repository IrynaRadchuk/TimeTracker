package ua.training.project.model.dao;

import java.util.Objects;

/**
 * DAO class to manage activity statistics in database
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityStatisticsDao)) return false;
        ActivityStatisticsDao that = (ActivityStatisticsDao) o;
        return users == that.users &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activity, category, users);
    }

    @Override
    public String toString() {
        return "ActivityStatisticsDao{" +
                "activity='" + activity + '\'' +
                ", category='" + category + '\'' +
                ", users=" + users +
                '}';
    }
}
