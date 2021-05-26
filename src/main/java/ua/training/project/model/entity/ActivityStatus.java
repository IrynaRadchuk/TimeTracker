package ua.training.project.model.entity;

public enum ActivityStatus {
    ABSENT("ABSENT"),PENDING("PENDING"),APPROVED("APPROVED");
    private String value;

    ActivityStatus(String value) {
        this.value = value;
    }
}
