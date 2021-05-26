package ua.training.project.model.dto;

import java.time.LocalDate;

public class UserActivityDTO {
    private String email;
    private String activity;
    private LocalDate date;
    private int duration;

    public UserActivityDTO() {
    }

    public UserActivityDTO(String email, String activity, LocalDate date, int duration) {
        this.email = email;
        this.activity = activity;
        this.date = date;
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
