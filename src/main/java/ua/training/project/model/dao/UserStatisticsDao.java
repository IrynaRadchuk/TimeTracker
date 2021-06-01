package ua.training.project.model.dao;

import ua.training.project.controller.command.Command;

import java.time.LocalDate;

/**
 * DAO class to get user statistics from database
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
}
