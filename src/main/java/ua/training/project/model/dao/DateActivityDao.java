package ua.training.project.model.dao;

import java.time.LocalDate;
import java.util.Objects;

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
    private String nameUa;

    public String getNameUa() {
        return nameUa;
    }

    public void setNameUa(String nameUa) {
        this.nameUa = nameUa;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateActivityDao)) return false;
        DateActivityDao that = (DateActivityDao) o;
        return id == that.id &&
                duration == that.duration &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, date, duration);
    }

    @Override
    public String toString() {
        return "DateActivityDao{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
