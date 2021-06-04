package ua.training.project.model.dao;

import java.time.LocalDate;
import java.util.Objects;

/**
 * DAO class to manage user statistics in database
 *
 * @author Iryna Radchuk
 */
public class UserStatisticsDao {
    private LocalDate date;
    private String email;
    private String firstName;
    private String lastName;
    private String activity;
    private int duration;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserStatisticsDao)) return false;
        UserStatisticsDao that = (UserStatisticsDao) o;
        return duration == that.duration &&
                Objects.equals(date, that.date) &&
                Objects.equals(email, that.email) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(activity, that.activity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, email, firstName, lastName, activity, duration);
    }

    @Override
    public String toString() {
        return "UserStatisticsDao{" +
                "date=" + date +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", activity='" + activity + '\'' +
                ", duration=" + duration +
                '}';
    }
}
