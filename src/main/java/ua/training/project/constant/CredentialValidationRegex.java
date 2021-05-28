package ua.training.project.constant;

public interface CredentialValidationRegex {
    String EMAIL_REGEX = "^[A-z0-9+_.-]+@(.+)$";
    String NAME_REGEX_EN = "^[A-Z][a-z]{2,20}$";
    String NAME_REGEX_UK = "^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{2,20}$";
    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,20}$";
    String DATE_REGEX = "[A-z]+\\s(?<key>\\b[A-z]+\\s+\\d+\\s+\\d+\\b)";
}
