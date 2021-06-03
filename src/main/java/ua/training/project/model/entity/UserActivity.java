package ua.training.project.model.entity;

import java.time.LocalDate;

public class UserActivity {
    private User user;
    private Activity activity;
    private LocalDate date;
    private int duration;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
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
    public String toString() {
        return "UserActivity{" +
                "user=" + user +
                ", activity=" + activity +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}
