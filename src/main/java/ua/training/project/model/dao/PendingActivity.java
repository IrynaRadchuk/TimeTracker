package ua.training.project.model.dao;

import java.util.Objects;

/**
 * DAO class to manage pending activities in database
 *
 * @author Iryna Radchuk
 */
public class PendingActivity {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String activityName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PendingActivity)) return false;
        PendingActivity activity = (PendingActivity) o;
        return userId == activity.userId &&
                Objects.equals(email, activity.email) &&
                Objects.equals(firstName, activity.firstName) &&
                Objects.equals(lastName, activity.lastName) &&
                Objects.equals(activityName, activity.activityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, firstName, lastName, activityName);
    }

    @Override
    public String toString() {
        return "PendingActivity{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", activityName='" + activityName + '\'' +
                '}';
    }
}
