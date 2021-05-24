package ua.training.project.model.services.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean validate(String regex, String input){
        Pattern p = Pattern.compile(regex);
        if (input == null) {
            return false;
        }
        Matcher m = p.matcher(input);
        return m.matches();
    }
}
