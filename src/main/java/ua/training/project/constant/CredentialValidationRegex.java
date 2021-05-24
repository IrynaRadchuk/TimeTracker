package ua.training.project.constant;

public interface CredentialValidationRegex {
    String EMAIL_REGEX = "^[A-z0-9+_.-]+@(.+)$";
    String NAME_REGEX_EN = "^[A-Z][a-z]{2,20}$";
    String NAME_REGEX_UK = "^[А-ЩЮЯҐІЇЄ][а-щьюяґіїє']{2,20}$";
    String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])${6,20}";
}
