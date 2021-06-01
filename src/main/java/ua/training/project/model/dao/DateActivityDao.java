package ua.training.project.model.dao;

import ua.training.project.controller.command.Command;
import ua.training.project.model.entity.Activity;

import java.time.LocalDate;

/**
 * DAO class to manage activities with dates in database
 *
 * @author Iryna Radchuk
 */
public class DateActivityDao {
    private int id;
    private String activity;
    private LocalDate date;
    private int duration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
